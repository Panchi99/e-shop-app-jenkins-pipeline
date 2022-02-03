FROM openjdk:11
ADD target/*.jar app.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar", "app.jar"]
