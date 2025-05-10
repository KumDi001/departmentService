FROM openjdk:17-alpine
EXPOSE 8082
ADD target/departmentservice-0.0.1.jar departmentservice.jar
ENTRYPOINT [ "java","-jar","/departmentservice.jar" ]