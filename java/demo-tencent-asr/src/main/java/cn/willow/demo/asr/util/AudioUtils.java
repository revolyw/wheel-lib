package cn.willow.demo.asr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * 音频工具类
 */
public class AudioUtils {

    /**
     * 获取音频文件时长（秒），使用 ffprobe 命令
     *
     * @param audioFilePath 音频文件路径
     * @return 时长（秒）
     * @throws FileNotFoundException 文件不存在
     */
    public static double getDurationSeconds(String audioFilePath) throws FileNotFoundException {
        File file = new File(audioFilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("音频文件不存在: " + audioFilePath);
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(
                "ffprobe", "-v", "error", "-show_entries", "format=duration",
                "-of", "default=noprint_wrappers=1:nokey=1", audioFilePath
            );
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            process.waitFor();

            if (line != null && !line.isEmpty()) {
                return Double.parseDouble(line);
            }
            throw new IllegalStateException("无法解析音频时长");
        } catch (Exception e) {
            throw new IllegalArgumentException("获取音频时长失败: " + e.getMessage(), e);
        }
    }

    /**
     * 检查音频是否超过限制（60秒）
     *
     * @param audioFilePath 音频文件路径
     * @return true=超限
     */
    public static boolean isOverLimit(String audioFilePath) throws FileNotFoundException {
        return getDurationSeconds(audioFilePath) > 60.0;
    }
}
