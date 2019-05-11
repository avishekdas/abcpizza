FROM java:8
VOLUME /tmp
ADD registry-0.0.1-SNAPSHOT.jar registry.jar
RUN bash -c 'touch /registry.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /registry.jar"]

EXPOSE 8761