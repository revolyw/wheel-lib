```shell
# 查看编译命令参数说明
javac
# 查看运行命令参数说明
java -help

# 编译包含 jar 包依赖的类
javac -cp 1.jar:2.jar dir1.Test.java

# 运行包含 jar 包依赖的类。与编译不同的是，运行在 classpath 中除了要指定依赖的 jar 包，同时也要指定运行字节码所在的目录，否则会找不到启动入口(main)
javac -cp 1.jar:2.jar:{dir1}:{dir2} dir1.Test

# 本程序编译运行示例
## 编译
java -cp jackson-core-2.9.6.jar:jackson-databind-2.9.6.jar:jackson-annotations-2.9.6.jar:/Users/Willow/Downloads/java Test
## 运行
java -cp jackson-core-2.9.6.jar:jackson-databind-2.9.6.jar:jackson-annotations-2.9.6.jar:/Users/Willow/github/wheel-lib/java/demo-compile-and-run Test
```



