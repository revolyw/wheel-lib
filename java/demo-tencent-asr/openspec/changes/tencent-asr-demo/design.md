## Context

腾讯云 ASR（语音识别）一句话识别 API 提供短音频（≤60秒，≤3MB）的语音转文字功能。本 Demo 用于验证 SDK 集成和 API 调用流程。

**技术栈：**
- Java 8+
- Maven
- Spring Boot（用于快速搭建可运行项目）
- tencentcloud-sdk-java 3.1.1477（一句话识别）
- tencentcloud-speech-sdk-java 1.0.52（极速版长音频识别）

**约束：**
- 音频文件 ≤3MB（API 限制）
- 音频时长 ≤60秒（API 限制）
- API 密钥通过环境变量注入，不硬编码

## Goals / Non-Goals

**Goals:**
- 实现本地 MP3 文件的一句话识别（≤60秒）
- 实现长音频识别（>60秒 ~ 2小时）使用极速版
- 验证 SDK 客户端初始化和凭证管理
- 提供可运行的 Spring Boot 应用示例

**Non-Goals:**
- 不实现流式识别
- 不处理极速版失败后的降级逻辑
- 不作为生产级应用

## Decisions

### 1. SDK vs 手写签名

**决定：** 使用腾讯云官方 SDK（tencentcloud-sdk-java）

**理由：**
- 简化签名鉴权实现（TC3-HMAC-SHA256 较复杂）
- 依赖成熟库，减少维护成本
- SDK 已包含 ASR 模块 `com.tencentcloudapi.asr.v20190614`

### 2. 项目结构

**决定：** Spring Boot Maven 项目

```
demo-tencent-asr/
├── pom.xml
├── src/main/java/cn/willow/demo/asr/
│   ├── DemoAsrApplication.java      # Spring Boot 启动类
│   ├── config/AsrConfig.java       # SDK 客户端配置
│   └── service/AsrService.java     # ASR 业务逻辑
└── src/main/resources/
```

**理由：**
- 复用现有项目结构风格（参考 demo-hystrix）
- Spring Boot 便于配置管理和依赖注入
- 便于后续扩展为 REST API

### 3. 环境变量命名

**决定：** `TENCENTCLOUD_SECRET_ID` 和 `TENCENTCLOUD_SECRET_KEY`

**理由：**
- 腾讯云官方 SDK 默认支持的环境变量命名规范
- 便于与其他腾讯云 SDK 项目统一

### 4. 音频参数

**决定：**
- `EngSerViceType`: `16k_zh`（中文通用引擎）
- `SourceType`: `1`（本地语音数据）
- `VoiceFormat`: `mp3`

### 5. 长音频识别（>60s）—— 录音文件识别极速版

**决定：** 超过 60 秒的音频使用录音文件识别极速版（Flash API）

**理由：**
- 异步轮询方式（CreateRecTask + DescribeTaskStatus）延迟高、实现复杂
- 极速版为同步接口，调用简单，30 分钟音频 10 秒内完成
- 极速版限制（2小时/100MB）远超"稍微超过60s"的实际场景
- 极速版失败时不降级到异步轮询方式

**技术选型：**
- SDK：`tencentcloud-speech-sdk-java`（非 tencentcloud-sdk-java）
- 核心类：`FlashRecognizer`
- 适用场景：60秒 ~ 2小时的音频

**接口地址：**
```
POST https://asr.cloud.tencent.com/asr/flash/v1/<appid>
```

**关键请求参数：**

| 参数 | 说明 | 示例值 |
|------|------|--------|
| appid | 腾讯云 AppID | 1251234567 |
| secretid | SecretId | - |
| engine_type | 引擎类型 | 16k_zh |
| voice_format | 音频格式 | mp3/wav/m4a |
| timestamp | 时间戳 | - |
| speaker_diarization | 说话人分离 | 0 |
| word_info | 词级时间戳 | 1 |

**SDK 调用示例：**
```java
// 初始化凭证
Credential credential = Credential.builder()
    .secretId(secretId)
    .secretKey(secretKey)
    .build();

// 创建极速识别器
FlashRecognizer recognizer = SpeechClient.newFlashRecognizer(appId, credential);

// 构造请求
FlashRecognitionRequest request = FlashRecognitionRequest.initialize();
request.setEngineType("16k_zh");
request.setFirstChannelOnly(1);
request.setVoiceFormat("mp3");

// 调用识别（同步返回）
FlashRecognitionResponse response = recognizer.recognize(request, audioData);
```

**实际包结构：**
- `com.tencent.SpeechClient` - 客户端工厂类
- `com.tencent.asr.model.Credential` - 凭证类
- `com.tencent.asr.model.FlashRecognitionRequest` - 请求类
- `com.tencent.asr.model.FlashRecognitionResponse` - 响应类
- `com.tencent.asr.service.FlashRecognizer` - 极速识别器服务类

## Risks / Trade-offs

| Risk | Mitigation |
|------|------------|
| 音频超过 3MB | Demo 使用 1.4MB 文件，合规 |
| 音频超过 60 秒 | Demo 使用短音频，合规 |
| 环境变量未设置 | 启动时检查并抛出明确异常 |
| SDK 版本更新 | 锁定 3.1.1477 版本 |
| 极速版超时/失败 | 不降级，直接抛异常 |

## Open Questions

无
