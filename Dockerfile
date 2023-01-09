FROM nexus.kblab.local:8083/v1/repositories/kblab/openjdk:11.0.3-jdk-slim-stretch
ARG DEPLOY_ENV
WORKDIR /app
ENV TZ Asia/Baku
COPY *.*ar .
RUN ln -sfn *.*ar app
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "./app", "--spring.profiles.active=${DEPLOY_ENV}"]
CMD [""]

