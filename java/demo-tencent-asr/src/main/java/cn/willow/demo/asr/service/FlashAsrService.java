package cn.willow.demo.asr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import com.tencent.SpeechClient;
import com.tencent.asr.model.Credential;
import com.tencent.asr.model.FlashRecognitionRequest;
import com.tencent.asr.model.FlashRecognitionResponse;
import com.tencent.asr.model.FlashRecognitionResponse.FlashRecognitionResult;
import com.tencent.asr.model.FlashRecognitionResponse.FlashRecognitionSentence;
import com.tencent.asr.service.FlashRecognizer;

/**
 * 录音文件识别极速版服务
 * 支持 >60 秒 ~ 2 小时的长音频识别
 * 同步接口，无需轮询
 */
@Service
public class FlashAsrService {

    private final String     appId;
    private final Credential credential;

    public FlashAsrService(@Value("${tencentcloud.asr.app-id}") String appId,
                           @Value("${tencentcloud.secret-id}") String secretId,
                           @Value("${tencentcloud.secret-key}") String secretKey) {
        this.appId = appId;
        this.credential = Credential.builder().secretId(secretId).secretKey(secretKey).build();
    }

    /**
     * 识别长音频文件（极速版）
     *
     * @param audioFilePath 音频文件路径
     * @return 识别结果
     * @throws FileNotFoundException 文件不存在
     * @throws IOException           读取文件失败
     */
    public String recognize(String audioFilePath) throws FileNotFoundException, IOException {
        File audioFile = new File(audioFilePath);
        if (!audioFile.exists()) {
            throw new FileNotFoundException("音频文件不存在: " + audioFilePath);
        }

        byte[] audioData = Files.readAllBytes(audioFile.toPath());

        FlashRecognizer recognizer = SpeechClient.newFlashRecognizer(appId, credential);

        FlashRecognitionRequest request = FlashRecognitionRequest.initialize();
        request.setEngineType("16k_zh");
        request.setFirstChannelOnly(1);
        request.setVoiceFormat(getFileExtension(audioFile));
        request.setSpeakerDiarization(0);
        request.setFilterDirty(0);
        request.setFilterModal(0);
        request.setFilterPunc(0);
        request.setConvertNumMode(1);
        request.setWordInfo(1);

        FlashRecognitionResponse response = recognizer.recognize(request, audioData);

        return formatResult(response);
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf(".");
        if (lastIndex == -1 || lastIndex == name.length() - 1) {
            return "";
        }
        return name.substring(lastIndex + 1);
    }

    /**
     * 格式化识别结果
     */
    private String formatResult(FlashRecognitionResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("识别结果:\n");

        if (response.getFlashResult() != null && !response.getFlashResult().isEmpty()) {
            for (FlashRecognitionResult result : response.getFlashResult()) {
                sb.append("  文本: ").append(result.getText()).append("\n");
                if (result.getSentenceList() != null && !result.getSentenceList().isEmpty()) {
                    for (FlashRecognitionSentence sentence : result.getSentenceList()) {
                        sb.append("    - 时间戳: ").append(sentence.getStartTime()).append("ms ~ ")
                            .append(sentence.getEndTime()).append("ms\n");
                        sb.append("      文本: ").append(sentence.getText()).append("\n");
                    }
                }
            }
        }

        sb.append("  请求ID: ").append(response.getRequestId()).append("\n");
        sb.append("  音频时长: ").append(response.getAudioDuration()).append("ms\n");
        if (response.getCode() != null && response.getCode() != 0) {
            sb.append("  错误码: ").append(response.getCode()).append("\n");
            sb.append("  错误信息: ").append(response.getMessage()).append("\n");
        }
        return sb.toString();
    }
}
