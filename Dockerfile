FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /libs/buyingfrenzy*.jar buyingfrenzy.jar
CMD [“java”,”-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/buyingfrenzy.jar”]