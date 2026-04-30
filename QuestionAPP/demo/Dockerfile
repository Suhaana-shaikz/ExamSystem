FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

# RUN ./mvnw clean package -DskipTests
RUN ./mvnw clean package -Dmaven.test.skip=true

EXPOSE 8081

CMD ["sh","-c","java -jar target/*.jar"]