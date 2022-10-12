FROM openjdk:11
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8083
ENTRYPOINT ["java","-jar","target/auth-service-0.0.1-SNAPSHOT.jar"]