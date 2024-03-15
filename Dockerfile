FROM openjdk:8
EXPOSE  8080
ADD target/springdeploy.jar springdeploy.jar
ENTRYPOINT ["java","-jar","/springdeploy.jar"]