# FROM eclipse-temurin:17-jdk
#
# WORKDIR /app
#
# COPY . .
#
# RUN chmod +x mvnw
#
# RUN ./mvnw clean package -Dmaven.test.skip=true
#
# EXPOSE 8087
#
# ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

# RUN ./mvnw clean package -DskipTests
RUN ./mvnw clean package -Dmaven.test.skip=true

EXPOSE 8087

CMD ["sh","-c","java -jar target/*.jar"]
#
# CMD ["java", "-jar", "target/*.jar"]