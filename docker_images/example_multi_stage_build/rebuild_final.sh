#!/bin/bash

# 定义镜像名称和容器名称
IMAGE_NAME="stage_final:v1"
CONTAINER_NAME="stage_final"

# 设置在命令返回非零状态时停止脚本执行
set -e

# 构建Docker镜像
docker build -t $IMAGE_NAME -f DockerfileFinal --build-arg BASE_IMAGE=stage_first:v1 .

# 检查并停止旧容器
if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
fi

# 运行新容器
CONTAINER_ID=$(docker run --name $CONTAINER_NAME -d $IMAGE_NAME)

# 进入容器
docker exec -it $CONTAINER_ID /bin/bash
