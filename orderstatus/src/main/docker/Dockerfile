FROM java:8
VOLUME /tmp
ADD orderstatus-0.0.1-SNAPSHOT.jar orderstatus.jar
RUN bash -c 'touch /orderstatus.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /orderstatus.jar"]

EXPOSE 9292