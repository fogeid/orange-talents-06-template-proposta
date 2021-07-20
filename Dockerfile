FROM openjdk:11-jdk
LABEL maintainer="Diego Batista"
WORKDIR /app
COPY target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]