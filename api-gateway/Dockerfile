FROM openjdk:11
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 4000
ENTRYPOINT ["java","-jar","target/api-gateway-0.0.1-SNAPSHOT.jar"]