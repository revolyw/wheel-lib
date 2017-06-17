# -*- coding:UTF-8 -*-

import urllib.request
import time
from bs4 import BeautifulSoup
import re
import os
import tempfile

class Properties:

    def __init__(self, file_name):
        self.file_name = file_name
        self.properties = {}
        try:
            fopen = open(self.file_name, 'r')
            for line in fopen:
                line = line.strip()
                if line.find('=') > 0 and not line.startswith('#'):
                    strs = line.split('=')
                    self.properties[strs[0].strip()] = strs[1].strip()
        except Exception as e:
            raise e
        else:
            fopen.close()

    # 判断是否包含该key# while page_index < 21449005:
    def has_key(self, key):
        return key in self.properties

    # 根据key读取value
    def get(self, key, default_value=''):
        if self.has_key(key):
            return self.properties[key]
        return default_value

    # 修改/添加key=value
    def put(self, key, value):
        self.properties[key] = value
        replace_property(self.file_name, key + '=.*', key + '=' + value, True)

def parse(file_name):
    return Properties(file_name)


def replace_property(file_name, from_regex, to_str, append_on_not_exists=True):
    file = tempfile.TemporaryFile(mode='w+t',encoding='utf-8')         #创建临时文件

    if os.path.exists(file_name):
        r_open = open(file_name,'r')
        pattern = re.compile(r'' + from_regex)
        found = None
        for line in r_open: #读取原文件
            if pattern.search(line) and not line.strip().startswith('#'):
                found = True
                line = re.sub(from_regex, to_str, line)
            file.write(line)   #写入临时文件
        if not found and append_on_not_exists:
            file.write('\n' + to_str)
        r_open.close()
        file.seek(0)

        content = file.read()  #读取临时文件中的所有内容

        if os.path.exists(file_name):
            os.remove(file_name)

        w_open = open(file_name,'w')
        w_open.write(str(content))   #将临时文件中的内容写入原文件
        w_open.close()

        file.close()  #关闭临时文件，同时也会自动删掉临时文件
    else:
        print("file %s not found" % file_name)

web_header = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'}

if __name__ == '__main__':
    file_path = '../properties/page_index.properties'
    props = parse(file_path)   #读取文件
    if not props.has_key('page_index'):
        print('read page_index error')
        exit(-1)
    page_index = ''
    try:
        page_index = int(props.properties.get('page_index'))
    except:
        print("convert string type(page_index) into int error")
        exit(-1)

    #  os.mkdir('doubanimages')
    print('spider starting work from ' + str(page_index))
    while page_index < 101622374:
        # 百度手机助手
        # http://shouji.baidu.com/software/11537454.html
        pageUrl = 'http://shouji.baidu.com/software/' + str(page_index) + '.html'
        try:
            page_req = urllib.request.Request(url=pageUrl, headers=web_header)
            page_file = urllib.request.urlopen(page_req)
            page_data = page_file.read().decode('utf-8')
            page_soup = BeautifulSoup(page_data, 'html.parser')
            # 下载链接
            tag_div_download = page_soup.find('div', {'class': 'area-download'})
            tag_a_download = tag_div_download.find('a',{'class': 'apk'})
            download_url = tag_a_download['href']
            if download_url is '':
                print(str(page_index) + 'ignore')
                page_index += 1
                continue
            # app名称
            tag_h1_app_name = page_soup.find('h1',{'class': 'app-name'})
            tag_span_app_name = tag_h1_app_name.find('span')
            app_name = tag_span_app_name.get_text()
            # app类型
            tag_a_list_app_type = page_soup.findAll('a',{'target': '_self'})
            tag_a_app_type = tag_a_list_app_type[2]
            app_type = tag_a_app_type.get_text()
            # app评分
            tag_span_app_score = page_soup.find('span',{'class': 'star-percent'})
            tag_span_style_app_score = tag_span_app_score['style']
            app_score = tag_span_style_app_score[6:-1]
            # app特色
            tag_span_app_features = page_soup.findAll('span', {'class': 'app-feature-detail'})
            tag_span_list_app_features = tag_span_app_features[0].findAll('span', {'class': 'res-tag-ok'})
            app_features = ''
            for tag_span_app_feature in tag_span_list_app_features:
                if tag_span_app_feature.get_text() == '\n' or tag_span_app_feature.get_text() == '':
                    continue
                app_features += tag_span_app_feature.get_text().replace('\n','') + ','
            app_features = app_features[0:-1]
            # app介绍
            tag_div_app_introduction = page_soup.find('div', {'class': 'introduction'})
            tag_p_app_introduction = tag_div_app_introduction.find('p', {'class': 'content'})
            app_introduction = tag_p_app_introduction.get_text()
            # app下载次数
            tag_span_app_download_num = page_soup.find('span', {'class': 'download-num'})
            app_download_num = tag_span_app_download_num.get_text()

            # app_all_info
            app_all_info = 'index:' + str(page_index) + '\n app_name:'+ app_name + '\n app_type:' + app_type + '\n app_score:' + app_score + '\n app_download_num:' + app_download_num + '\n app_features:' + app_features + '\n app_introduction:' + app_introduction + ', download_url:' + download_url
            # print(app_all_info)
            try:
                time.sleep(1)
                print(time.strftime("%H:%M:%S ") + 'downloading: ' + str(page_index))
                req = urllib.request.Request(download_url, headers=web_header)
                webPage = urllib.request.urlopen(req, timeout=10)
                data = webPage.read()
                file = open('../apks/' + str(page_index) + '.apk', "wb")
                file.write(data)
                info = open('../apks/app_info_'+str(page_index), "wb")
                info.write(bytes(app_all_info,"utf-8"))
                page_index += 1
            except:
                print('download ' + str(page_index) + ' apk file error')
                file.flush()
                file.close()
                # info.flush()
                # info.close()
                page_index += 1
            else:
                file.flush()
                file.close()
                # info.flush()
                # info.close()
            page_index += 1
        except:
            page_index += 1
            props.put('page_index', str(page_index))
        else:
            page_index += 1

        props.put('page_index', str(page_index))
