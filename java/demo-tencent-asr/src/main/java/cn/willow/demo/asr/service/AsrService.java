package cn.willow.demo.asr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * ASR 服务类
 * 提供本地音频文件的一句话识别功能
 */
@Service
public class AsrService {

    private final AsrClient asrClient;

    @Autowired
    public AsrService(AsrClient asrClient) {
        this.asrClient = asrClient;
    }

    /**
     * 识别本地音频文件
     *
     * @param audioFilePath 音频文件路径（支持 MP3 格式）
     * @return 识别结果
     * @throws FileNotFoundException 音频文件不存在
     * @throws IOException            读取音频文件失败
     * @throws TencentCloudSDKException 腾讯云 API 调用失败
     */
    public String recognize(String audioFilePath) throws FileNotFoundException, IOException, TencentCloudSDKException {
        File audioFile = new File(audioFilePath);
        if (!audioFile.exists()) {
            throw new FileNotFoundException("音频文件不存在: " + audioFilePath);
        }

        // 读取文件字节
        byte[] audioData = Files.readAllBytes(audioFile.toPath());

        // Base64 编码
        String base64Data = Base64.getEncoder().encodeToString(audioData);
        int dataLen = audioData.length;

        // 构建请求
        SentenceRecognitionRequest request = new SentenceRecognitionRequest();
        request.setEngSerViceType("16k_zh"); // 中文通用引擎
        request.setSourceType(1L); // 本地语音数据
        request.setVoiceFormat("mp3");
        request.setData(base64Data);
        request.setDataLen((long) dataLen);

        // 调用 API
        SentenceRecognitionResponse response = asrClient.SentenceRecognition(request);

        // 解析响应
        return formatResult(response);
    }

    /**
     * 格式化识别结果
     */
    private String formatResult(SentenceRecognitionResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("识别结果:\n");
        sb.append("  文本: ").append(response.getResult()).append("\n");
        sb.append("  时长: ").append(response.getAudioDuration()).append(" ms\n");
        sb.append("  请求ID: ").append(response.getRequestId()).append("\n");
        return sb.toString();
    }
}
