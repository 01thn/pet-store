FROM openjdk:11
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8888
ENTRYPOINT ["java","-jar","target/cloud-config-0.0.1-SNAPSHOT.jar"]