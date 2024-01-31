FROM ubuntu:18.04

# 设置维护者信息
LABEL maintainer="yangw <yangw@dxy.cn>"

# 安装 rsyslog、tcpdump
RUN apt-get update && \
    apt-get install -y rsyslog && \
	apt-get install -y tcpdump && \
    rm -rf /var/lib/apt/lists/*

# 创建rsyslog的工作目录并设置权限
RUN mkdir -p /var/lib/rsyslog && \
	chown syslog:syslog /var/lib/rsyslog && \
	chown syslog:syslog /var/log

# 复制 rsyslog 配置文件到容器中
COPY rsyslog.conf /etc/rsyslog.conf

# 查看配置文件
RUN cat /etc/rsyslog.conf

# 配置 rsyslog 启动时使用的命令
CMD ["rsyslogd", "-n"]