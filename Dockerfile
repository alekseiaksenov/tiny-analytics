FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY target/t-analytics-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]