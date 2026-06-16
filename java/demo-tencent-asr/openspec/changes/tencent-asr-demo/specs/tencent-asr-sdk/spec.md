## ADDED Requirements

### Requirement: SDK 客户端初始化

系统 SHALL 通过环境变量 `TENCENTCLOUD_SECRET_ID` 和 `TENCENTCLOUD_SECRET_KEY` 创建腾讯云凭证，并初始化 `AsrClient` 客户端用于 ASR API 调用。

### Requirement: 本地音频识别

系统 SHALL 支持从本地 MP3 文件读取语音数据，通过 Base64 编码后调用腾讯云 ASR 一句话识别 API，并将识别结果返回给调用方。

#### Scenario: 成功识别音频
- **WHEN** 用户提供有效的 MP3 文件路径（≤3MB，≤60秒）
- **THEN** 系统返回识别文本内容，包含请求 ID 和音频时长

#### Scenario: 环境变量未配置
- **WHEN** `TENCENTCLOUD_SECRET_ID` 或 `TENCENTCLOUD_SECRET_KEY` 环境变量未设置
- **THEN** 系统抛出明确异常，提示缺少必需的环境变量

#### Scenario: 文件不存在
- **WHEN** 提供的音频文件路径不存在
- **THEN** 系统抛出 `FileNotFoundException`

### Requirement: 识别结果解析

系统 SHALL 解析腾讯云 ASR 响应，包含：
- `Result`: 识别文本
- `AudioDuration`: 音频时长（毫秒）
- `RequestId`: 请求唯一标识

### Requirement: 音频数据处理

系统 SHALL 将音频文件读取为字节数组，计算其 Base64 编码字符串和数据长度，分别赋值给请求的 `Data` 和 `DataLen` 字段。
