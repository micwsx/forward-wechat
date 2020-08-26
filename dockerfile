FROM java:8
#VOLUME /temp
# ADD ["target/forward-wechat-1.0.jar","app.jar"]
ADD ["forward-wechat-1.0.jar","app.jar"]
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]