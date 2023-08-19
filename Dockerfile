FROM amazoncorretto:17-al2-generic-jdk
WORKDIR /app
COPY  . .
ENTRYPOINT ["java", "-jar", "./LiaVulcanBot.jar"]
