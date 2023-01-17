FROM eclipse-temurin:17.0.4.1_1-jdk-focal
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY ./target/notetaking-0.0.1-SNAPSHOT.jar notetaking.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar notetaking.jar