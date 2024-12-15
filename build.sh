## 服务器上用这个命令
docker build --build-arg JAR_VERSION=1.0 -t haoy77/management-backend:1.0 .

## 本机命令
#docker build --build-arg JAR_VERSION=1.0 -t haoy77/management-backend:1.0 .

# 兼容 amd、arm 构建镜像
# docker buildx build --load --platform liunx/amd64,linux/arm64 -t xiaofuge/xfg-frame-archetype-app:1.0 -f ./Dockerfile . --push