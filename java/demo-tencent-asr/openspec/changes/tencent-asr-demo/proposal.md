## Why

需要编写一个腾讯云 ASR（语音识别）一句话识别 API 的 Java Demo，用于验证 SDK 集成和 API 调用流程。

## What Changes

- 新增 Maven 项目结构（Spring Boot 风格）
- 集成 `tencentcloud-sdk-java` 依赖（短音频识别）
- 集成 `tencentcloud-speech-sdk-java` 依赖（极速版长音频识别）
- 实现基于本地音频文件的一句话识别功能（≤60秒）
- 实现长音频识别（>60秒）使用录音文件识别极速版
- 通过环境变量 `TENCENTCLOUD_SECRET_ID` 和 `TENCENTCLOUD_SECRET_KEY` 注入 API 密钥
- 支持 MP3 格式音频（44.1kHz, 192kbps, 单声道）
- 测试文件：`~/Downloads/tmp/AUDIO202603201210484160000320262.mp3`（约 1.4MB，符合 ≤3MB 限制）

## Capabilities

### New Capabilities

- `tencent-asr-sdk`: 腾讯云 ASR SDK 集成，包含客户端初始化、凭证管理和请求发送
- `tencent-asr-flash`: 录音文件识别极速版，用于长音频（>60s）的同步识别

## Impact

- 新增依赖：`com.tencentcloudapi:tencentcloud-sdk-java`
- 无破坏性变更（纯新增 Demo）
- 测试文件需用户自行准备腾讯云账号和开通 ASR 服务
