FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /build/libs/buyingfrenzy-0.0.1-SNAPSHOT.jar buyingfrenzy.jar
CMD [“java”,”-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/buyingfrenzy.jar”]