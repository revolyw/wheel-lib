#!/bin/bash

# 定义镜像名称和容器名称
IMAGE_NAME="rsyslog:v1"
CONTAINER_NAME="rsyslog"

# 构建Docker镜像
docker build -t $IMAGE_NAME .

# 检查并停止旧容器
if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
fi

# 运行新容器
CONTAINER_ID=$(docker run --name $CONTAINER_NAME -p 5145:5145/udp -d $IMAGE_NAME)

# 发送两条 syslog
echo "this is an log" | nc -u -w 0 127.0.0.1 5145
echo "this is an log" | nc -u -w 0 127.0.0.1 5145

# 进入容器
docker exec -it $CONTAINER_ID /bin/bash