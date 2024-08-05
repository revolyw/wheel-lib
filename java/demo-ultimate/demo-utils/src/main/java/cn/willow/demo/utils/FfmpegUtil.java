package cn.willow.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ffmpeg 多媒体工具
 *
 * @author willow
 * @date 2024/7/3
 * @see <a href=https://ffmpeg.org//documentation.html></a>
 */
public class FfmpegUtil {
    /**
     * FFmpeg的安装路径，或者如果FFmpeg在系统PATH变量中，可以直接使用"ffmpeg"
     */
    private static final String FFMPEG_PATH = "/Users/willow/software/ffmpeg";

    /**
     * 压缩视频
     *
     * @param inputPath  视频本地路径或远端地址
     * @param outputPath 压缩视频输出地址
     * @param bitrate    码率
     * @param width      宽
     * @param height     高
     * @param startSecond 裁剪起始秒
     * @param durationSeconds 裁剪秒数
     */
    public static void compressVideo(String inputPath, String outputPath, int bitrate, int width, int height,
                              double startSecond, int durationSeconds) {
        List<String> command = new ArrayList<>();
        command.add(FFMPEG_PATH);
        command.add("-i"); // 输入文件
        command.add(inputPath);
        // 视频裁剪
        command.add("-ss"); // 裁剪开始秒
        command.add(String.format("%.2f", startSecond));
        command.add("-t"); // 裁剪持续秒数
        command.add(durationSeconds + "");
        // 视频码率
        command.add("-b:v");
        command.add(bitrate + "k");
        // 设置输出视频的分辨率
        command.add("-s");
        command.add(width + "x" + height);
        // 严格模式
        command.add("-strict");
        command.add("2");
        // 覆盖输出文件
        command.add("-y");
        command.add(outputPath);

        try {
            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(true) // 将错误流重定向到标准输出流
                    .start();

            System.out.println("start ffmpeg process..." + LocalTime.now());
            printProcessOutput(process.getInputStream());
            // 等待进程执行完成
            process.waitFor();
            // 检查进程是否成功完成
            if (process.exitValue() == 0) {
                System.out.println("ffmpeg process completed" + LocalTime.now());
                return;
            }
            System.out.println("ffmpeg process interrupted");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩视频
     *
     * @param params ffmpeg 参数
     */
    public static void compressVideo(String params) {
        List<String> command = new ArrayList<>();
        String[] paramArray = params.split(" ");
        command.add(FFMPEG_PATH);
        command.addAll(Arrays.asList(paramArray));

        Process process = null;
        try {
            process = new ProcessBuilder(command)
                    .redirectErrorStream(true) // 将错误流重定向到标准输出流
                    .start();

            System.out.println("start ffmpeg process..." + LocalTime.now());
            printProcessOutput(process.getInputStream());
            // 等待进程执行完成
            process.waitFor();
            // 检查进程是否成功完成
            if (process.exitValue() == 0) {
                System.out.println("ffmpeg process completed" + LocalTime.now());
                return;
            }
            System.out.println("ffmpeg process interrupted");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != process) {
                process.destroy();
            }
        }
    }

    // 辅助方法，用于获取进程输出
    private static void printProcessOutput(InputStream inputStream) {
        // 创建并启动一个新线程来读取和打印FFmpeg的输出
        Thread outputPrinter = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // 实时打印输出
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        outputPrinter.start();
    }

    // 调用示例
    public static void main(String[] args) {
//        ffmpegBySpecificParams();
        ffmpegByParams();
    }

    private static void ffmpegByParams() {
        // 本地视频位置/远端视频 URL
        String remoteVideoUrl = "/Users/willow/Downloads/4K60FPS视频/45s350M.MOV";
        // 压缩后视频保存的路径
        String compressedVideoPath = "/Users/willow/Downloads/ffmpeg_compressed_video.mp4";
        String params =  "-i " + remoteVideoUrl + " -ss 5.00 -t 30 -b:v 2000k -s 720x1080 -strict 2 -y " + compressedVideoPath;
        // 压缩视频
        FfmpegUtil.compressVideo(params);
    }

    private static void ffmpegBySpecificParams() {
        // 本地视频位置/远端视频 URL
        String remoteVideoUrl = "/Users/willow/Downloads/4K60FPS视频/45s350M.MOV";
        // 压缩后视频保存的路径
        String compressedVideoPath = "/Users/willow/Downloads/ffmpeg_compressed_video.mp4";
        // 设置目标视频的码率，例如：200kbps
        int targetBitrate = 2000;
        int width = 720;
        int height = 1080;
        double startSecond = 5.00;
        int durationSeconds = 30;

        // 压缩视频
        FfmpegUtil.compressVideo(remoteVideoUrl, compressedVideoPath, targetBitrate, width, height, startSecond, durationSeconds);
    }
}
