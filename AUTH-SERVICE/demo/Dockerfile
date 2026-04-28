FROM eclipse-temurin:17-jdk

WORKDIR /app

# copy full project
COPY . .

# give permission
RUN chmod +x mvnw

# build jar (skip tests)
RUN ./mvnw clean package -Dmaven.test.skip=true

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]