FROM openjdk:8-alpine
VOLUME /tmp
ADD target/taskApp-0.0.1-SNAPSHOT.jar taskApp-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/taskApp-0.0.1-SNAPSHOT.jar"]