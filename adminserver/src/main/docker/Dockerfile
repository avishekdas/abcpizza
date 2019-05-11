FROM java:8
VOLUME /tmp
ADD adminserver-1.3.3.RELEASE.jar adminserver.jar
RUN bash -c 'touch /adminserver.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /adminserver.jar"]

EXPOSE 2222
