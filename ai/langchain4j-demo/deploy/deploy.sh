#!/bin/bash
#mvn clean package -Dmaven.test.skip=true
APP_NAME="langchain4j-demo"
USERNAME="ubuntu"
HOST="118.89.191.13"
PORT="8888"
MAX_TRIES=3
TRY=0

scp ./target/${APP_NAME}-1.0.0-SNAPSHOT.jar ubuntu@118.89.191.13:/home/ubuntu/
# 完整的 ssh 命令，用于执行远程命令
SSH_CMD="ssh $USERNAME@$HOST"

# 循环直到成功杀死进程或达到最大尝试次数
while [ $TRY -lt $MAX_TRIES ]; do
    # 查询占用指定端口的进程
    PROCESS=$($SSH_CMD "lsof -i :$PORT | grep LISTEN")

    if [ -z "${PROCESS}" ]; then
        echo "未查询到 ${PORT} 端口上的进程"
        break
    else
        # 打印查询到的进程
        echo "查询到占用 ${PORT} 端口的进程："
        echo "${PROCESS}"

        # 提取进程 ID
        PID=$(echo "${PROCESS}" | awk '{print $2}')

        # 杀死进程
        $SSH_CMD "kill -9 ${PID}"

        # 等待一段时间，让系统处理杀死的进程
        sleep 2

        # 检查进程是否被杀死
        if $SSH_CMD "lsof -i :${PORT} | grep LISTEN &> /dev/null"; then
            echo "进程没有被杀死，正在重试..."
            ((TRY++))
        else
            echo "进程已被杀死"
            break
        fi
    fi
done

# 如果超过最大尝试次数，打印失败信息
if [ $TRY -eq $MAX_TRIES ]; then
    echo "在尝试了 ${MAX_TRIES} 次之后，进程仍未被杀死"
    exit 1
fi

echo "killing process waiting 5 seconds ..."
sleep 5
ssh ubuntu@118.89.191.13 "rm ${APP_NAME}.out"
echo "${APP_NAME} starting ..."
ssh ubuntu@118.89.191.13 "nohup java -Dspring.profiles.active=prd -jar ./${APP_NAME}-1.0.0-SNAPSHOT.jar > ${APP_NAME}.out 2>&1 &"
ssh ubuntu@118.89.191.13 "tail -f ${APP_NAME}.out"
