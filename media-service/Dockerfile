FROM openjdk:11
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8081
ENTRYPOINT ["java","-jar","target/media_service-0.0.1-SNAPSHOT.jar"]