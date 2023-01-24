FROM eclipse-temurin:17.0.4.1_1-jdk-focal
EXPOSE 8080
ENV PORT 8080
COPY target/*.jar /opt/notetaking.jar
ADD target/classes/templates /opt
WORKDIR /opt
ENTRYPOINT exec java $JAVA_OPTS -jar notetaking.jar