FROM java:8
VOLUME /tmp
ADD monitor-0.0.1-SNAPSHOT.jar monitor.jar
RUN bash -c 'touch /monitor.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /monitor.jar"]

EXPOSE 7979