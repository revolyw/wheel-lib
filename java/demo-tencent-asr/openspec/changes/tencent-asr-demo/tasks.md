## 1. 项目初始化

- [x] 1.1 创建 Maven 项目目录结构
- [x] 1.2 创建 `pom.xml` 并添加 `tencentcloud-sdk-java` 依赖（版本 3.1.1477）和 Spring Boot 依赖
- [x] 1.3 创建 Spring Boot 启动类 `DemoAsrApplication.java`

## 2. 配置层

- [x] 2.1 创建 `AsrConfig.java`，实现 `CommandLineRunner` 接口
- [x] 2.2 在 `AsrConfig` 中从环境变量读取 `TENCENTCLOUD_SECRET_ID` 和 `TENCENTCLOUD_SECRET_KEY`
- [x] 2.3 创建 `AsrClient` 实例并注入为 Spring Bean

## 3. 服务层

- [x] 3.1 创建 `AsrService.java` 服务类
- [x] 3.2 实现 `recognize(String audioFilePath)` 方法，读取本地音频文件
- [x] 3.3 实现 Base64 编码和数据封装逻辑
- [x] 3.4 调用 `AsrClient.SentenceRecognition()` 并解析响应

## 4. 测试验证

- [x] 4.1 配置测试音频文件路径：`~/Downloads/tmp/AUDIO202603201210484160000320262.mp3`
- [x] 4.2 运行应用并验证识别结果输出
- [x] 4.3 验证环境变量缺失时的异常处理

## 5. 长音频极速版（>60秒）

- [x] 5.1 在 `pom.xml` 中添加 `tencentcloud-speech-sdk-java` 依赖（版本 1.0.52）
- [x] 5.2 创建 `FlashAsrService.java` 服务类
- [x] 5.3 实现 `FlashRecognizer` 客户端初始化
- [x] 5.4 实现极速版 `recognize()` 方法，调用 `recognizer.recognize()`
- [x] 5.5 修改 `AsrConfig` 中的路由逻辑：>60s 使用 `FlashAsrService` 替代 `LongAsrService`
- [x] 5.6 移除 `LongAsrService`（异步轮询方式，不再使用）
- [x] 5.7 测试 >60s 音频的极速版识别（需要你运行测试）
