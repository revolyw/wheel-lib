# webpack
1. npm初始化 npm init
2. 安装webpack npm install webpack --save-dev
3. 使用webpack打包 webpack hello.js hello.bundle.js
4. 使用webpack相关参数打包  webpack hello.js hello.bundle.js --module-bind 'css=style-loader!css-loader' --progress --display-modules --display-reasons --watch
	- --module-bin 'css=style-loader!css-loader' 打包时绑定相关模块，如果是css文件则使用style-loader，使用style-loader之前先用css-loader处理
	- --progress 打包过程、进度等
	- --display-modules 显示打包的各个modules
	- --display-reasons 显示为什么要打包各个modules
	- --watch 监听源文件变化，实时输出打包文件
