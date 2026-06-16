package cn.willow.demo.asr.config;

import cn.willow.demo.asr.service.AsrService;
import cn.willow.demo.asr.service.FlashAsrService;
import cn.willow.demo.asr.util.AudioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * ASR 配置类
 * 负责初始化腾讯云 ASR 客户端，并在启动时运行识别测试
 */
@Configuration
public class AsrConfig implements CommandLineRunner {

    @Value("${asr.audio-file-path}")
    private String audioFilePath;

    @Bean
    public AsrClient asrClient() {
        String secretId = firstNonBlank(System.getProperty("TENCENTCLOUD_SECRET_ID"),
            System.getenv("TENCENTCLOUD_SECRET_ID"));
        String secretKey = firstNonBlank(System.getProperty("TENCENTCLOUD_SECRET_KEY"),
            System.getenv("TENCENTCLOUD_SECRET_KEY"));

        if (secretId == null || secretId.isEmpty()) {
            throw new IllegalStateException(
                "环境变量 TENCENTCLOUD_SECRET_ID 未设置（支持 JVM option: -DTENCENTCLOUD_SECRET_ID=xxx）");
        }
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException(
                "环境变量 TENCENTCLOUD_SECRET_KEY 未设置（支持 JVM option: -DTENCENTCLOUD_SECRET_KEY=xxx）");
        }

        Credential credential = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("asr.ap-guangzhou.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        // 使用默认的腾讯云地域（广州）
        return new AsrClient(credential, "ap-guangzhou", clientProfile);
    }

    @Autowired
    @Lazy
    private AsrService     asrService;

    @Autowired
    @Lazy
    private FlashAsrService flashAsrService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ASR 配置加载完成，AsrClient 已初始化");
        System.out.println("开始识别音频文件: " + audioFilePath);

        // 验证音频时长
        double duration = 0;
        try {
            duration = AudioUtils.getDurationSeconds(audioFilePath);
            System.out.printf("音频时长: %.3f 秒%n", duration);
        } catch (Exception e) {
            System.err.println("无法获取音频时长: " + e.getMessage());
        }

        // 根据时长选择识别方式
        if (duration > 60.0) {
            System.out.println("使用录音文件识别极速版 API（支持长音频）...");
            try {
                String result = flashAsrService.recognize(audioFilePath);
                System.out.println("\n===== 识别结果 =====");
                System.out.println(result);
                System.out.println("====================\n");
            } catch (Exception e) {
                System.err.println("音频识别失败: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("使用一句话识别 API...");
            try {
                String result = asrService.recognize(audioFilePath);
                System.out.println("\n===== 识别结果 =====");
                System.out.println(result);
                System.out.println("====================\n");
            } catch (Exception e) {
                System.err.println("音频识别失败: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static String firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.isEmpty()) {
                return v;
            }
        }
        return null;
    }
}
