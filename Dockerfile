FROM java:8-jre

ENV TimeZone=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone

ADD ./target/leaveword-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/leaveword-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081