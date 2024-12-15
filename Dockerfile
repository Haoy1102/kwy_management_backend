# 基础镜像
FROM openjdk:8
# 作者
MAINTAINER haoy77
# 环境配置
ENV PARAMS=""
ENV myapp.file-path=/app/temporary/
ENV myapp.template-order-path=/app/template/order.xlsx
ENV myapp.template-checkForm-path=/app/template/checkForm.xlsx
ENV spring.profiles.active=staging
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 暴露端口
EXPOSE 82
# 定义构建参数
ARG JAR_VERSION
# 设置工作目录
WORKDIR /app
# 添加应用
# 复制模板文件夹
COPY template /app/template
COPY temporary /app/temporary

# 添加应用
ADD target/management-${JAR_VERSION}.jar /management-backend-1.0.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /management-backend-1.0.jar $PARAMS"]