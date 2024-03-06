FROM ubuntu:18.04
# 设置维护者信息
LABEL maintainer="yangw <yangw@dxy.cn>"

RUN mkdir -p /root/skywalking-agent && \
	touch /root/skywalking-agent/file && \
	echo "this is a file" > /root/skywalking-agent/file && \
	rm -rf /tmp/*

# CMD 指定一个长时间运行的命令
CMD ["tail", "-f", "/dev/null"]
