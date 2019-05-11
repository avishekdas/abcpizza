FROM java:8
VOLUME /tmp
ADD gateway-2.1.0.RELEASE.jar gateway.jar
RUN bash -c 'touch /gateway.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gateway.jar"]

EXPOSE 8765