FROM openjdk:11
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8082
ENTRYPOINT ["java","-jar","target/private-0.0.1-SNAPSHOT.jar"]