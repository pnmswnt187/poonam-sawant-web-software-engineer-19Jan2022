FROM openjdk:8u312-jdk
LABEL maintainer='Poonam Sawant'
EXPOSE 8081
COPY /build/libs/buyingfrenzy-0.0.1-SNAPSHOT.jar /usr/local/webapps/buyingfrenzy.jar
WORKDIR /usr/local/webapps
CMD ["java", "-DSpring.profiles.active=default", "-jar", "buyingfrenzy.jar"]