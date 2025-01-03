FROM openjdk:17-jdk

WORKDIR /market

COPY target/article-management-system-0.0.1-SNAPSHOT.jar  /market/ararticle-management-system.jar

EXPOSE 8081

CMD ["java", "-jar", "ararticle-management-system.jar"]