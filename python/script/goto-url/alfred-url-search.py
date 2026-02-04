#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Alfred workflow for fuzzy searching URLs and opening them in default browser.
配置格式: JSON文件包含键值对，键是搜索关键词，值是URL
"""

import json
import os
import sys
import subprocess
import argparse
from typing import Dict, List, Tuple
import difflib


class AlfredURLSearch:
    def __init__(self, config_path: str = None):
        """初始化Alfred URL搜索器

        Args:
            config_path: 配置文件路径，如果为None则使用默认路径
        """
        if config_path is None:
            # 使用脚本所在目录的配置文件
            script_dir = os.path.dirname(os.path.abspath(__file__))
            self.config_path = os.path.join(script_dir, "alfred_url_config.json")
        else:
            self.config_path = config_path

        self.config = self.load_config()

    def load_config(self) -> Dict[str, str]:
        """加载配置文件

        Args:
            配置字典，键为关键词，值为URL
        """
        if not os.path.exists(self.config_path):
            # 创建默认配置文件
            default_config = {
                "github": "https://github.com",
                "google": "https://google.com",
                "stackoverflow": "https://stackoverflow.com",
                "python docs": "https://docs.python.org",
                "alfred docs": "https://www.alfredapp.com/help",
                "example": "https://example.com",
            }
            self.save_config(default_config)
            return default_config

        try:
            with open(self.config_path, "r", encoding="utf-8") as f:
                return json.load(f)
        except (json.JSONDecodeError, IOError) as e:
            return {}

    def save_config(self, config: Dict[str, str]) -> None:
        """保存配置文件

        Args:
            config: 配置字典
        """
        try:
            with open(self.config_path, "w", encoding="utf-8") as f:
                json.dump(config, f, indent=2, ensure_ascii=False)
        except IOError as e:
            print(f"Error saving config: {e}")

    def fuzzy_search(self, query: str, limit: int = 10) -> List[Tuple[str, str, float]]:
        """简化的搜索算法：关键词作为子串匹配到key就返回

        Args:
            query: 搜索关键词
            limit: 返回结果数量限制

        Returns:
            列表，每个元素为(关键词, URL, 匹配分数)
        """
        # 如果配置为空，直接返回空结果
        if not self.config:
            return []

        if not query or query == "{query}":
            # 返回所有配置项，按字母排序
            items = [(key, value, 1.0) for key, value in self.config.items()]
            items.sort(key=lambda x: x[0].lower())
            return items[:limit]

        query_lower = query.lower().strip()
        results = []

        # 简单遍历，匹配就添加
        for key, value in self.config.items():
            key_lower = key.lower()

            # 只要关键词是key的子串就匹配
            if query_lower in key_lower:
                position = key_lower.find(query_lower)
                score = 0.8 - (position * 0.01) + (len(query) * 0.05)
                results.append((key, value, score))

        # 按分数降序排序
        results.sort(key=lambda x: -x[2])
        return results[:limit]

    def format_alfred_json(self, results: List[Tuple[str, str, float]]) -> Dict:
        """将搜索结果格式化为Alfred JSON格式

        Args:
            results: 搜索结果列表

        Returns:
            Alfred JSON格式的字典
        """
        items = []
        for key, url, score in results:
            items.append(
                {
                    "uid": key,
                    "title": key,
                    "subtitle": url,
                    "arg": url,  # 传递给下一个动作的参数
                    "match": f"{key} {url}",  # 同时匹配关键词和URL
                    "autocomplete": key,
                    "valid": True,
                    "icon": {
                        "path": "icon.png"  # 需要Alfred workflow中包含此图标
                    },
                }
            )
        if not results:
            return {"items": [{"title": "未找到匹配的 URL", "valid": False}]}
        return {"items": items}

    def open_url_in_browser(self, url: str) -> None:
        """在默认浏览器中打开URL

        Args:
            url: 要打开的URL
        """
        try:
            # macOS上使用open命令在默认浏览器中打开URL
            subprocess.run(["open", url], check=True)
        except subprocess.CalledProcessError as e:
            print(f"Error opening URL: {e}")

    def add_config(self, key: str, url: str) -> bool:
        """添加新的配置项

        Args:
            key: 关键词
            url: URL

        Returns:
            是否成功添加
        """
        if not key or not url:
            return False

        self.config[key] = url
        self.save_config(self.config)
        return True

    def remove_config(self, key: str) -> bool:
        """删除配置项

        Args:
            key: 要删除的关键词

        Returns:
            是否成功删除
        """
        if key in self.config:
            del self.config[key]
            self.save_config(self.config)
            return True
        return False


def main():
    try:
        # 最简单的参数处理：如果sys.argv[1]存在且不是以--开头，就是查询
        query = None
        if len(sys.argv) > 1:
            if not sys.argv[1].startswith("--"):
                query = sys.argv[1]

        if query is not None:
            # 如果查询是字面字符串'{query}'，返回空结果
            if query == "{query}":
                print(json.dumps({"items": []}))
                return

            # 创建搜索器并搜索
            searcher = AlfredURLSearch()
            results = searcher.fuzzy_search(query)
            alfred_json = searcher.format_alfred_json(results)
            print(json.dumps(alfred_json))
            return

        # 如果没有直接传递查询，使用argparse
        parser = argparse.ArgumentParser(description="Alfred URL Search Workflow")
        parser.add_argument("--query", type=str, help="Search query from Alfred")
        parser.add_argument("--config", type=str, help="Custom config file path")
        parser.add_argument(
            "--add", nargs=2, metavar=("KEY", "URL"), help="Add new config entry"
        )
        parser.add_argument("--remove", type=str, help="Remove config entry")
        parser.add_argument(
            "--list", action="store_true", help="List all config entries"
        )
        parser.add_argument("--open", type=str, help="Open URL directly")

        args = parser.parse_args()

        # 初始化搜索器
        searcher = AlfredURLSearch(args.config)

        # 处理添加操作
        if args.add:
            key, url = args.add
            if searcher.add_config(key, url):
                print(f"Added: {key} -> {url}")
            else:
                print("Failed to add config")
            return

        # 处理删除操作
        if args.remove:
            if searcher.remove_config(args.remove):
                print(f"Removed: {args.remove}")
            else:
                print(f"Config not found: {args.remove}")
            return

        # 处理列表操作
        if args.list:
            for key, url in searcher.config.items():
                print(f"{key}: {url}")
            return

        # 处理直接打开操作
        if args.open:
            searcher.open_url_in_browser(args.open)
            return

        # 处理--query参数（命令行使用）
        if args.query is not None:
            results = searcher.fuzzy_search(args.query)
            alfred_json = searcher.format_alfred_json(results)
            print(json.dumps(alfred_json))
            return

        # 如果没有参数，显示使用说明
        parser.print_help()

    except Exception as e:
        # 返回空结果而不是崩溃
        print(json.dumps({"items": []}))


if __name__ == "__main__":
    main()
