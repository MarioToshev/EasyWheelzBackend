FROM gradle:7.5-jdk17
WORKDIR /opt/app
COPY ./build/libs/EasyWheelz-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080

ENTRYPOINT ["sh", "-c","java ${JAVA_OPTS} -jar EasyWheelz-0.0.1-SNAPSHOT.jar "]

#ENTRYPOINT ["java","-Dspring.profiles.activate=staging","-jar","/app.jar"]
