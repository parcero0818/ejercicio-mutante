FROM openjdk:13-jdk
VOLUME /tmp
ADD ./build/libs/*.jar app.jar
EXPOSE 8199
ENTRYPOINT ["java", "-jar", "/app.jar"]