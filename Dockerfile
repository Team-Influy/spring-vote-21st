# build stage
FROM amazoncorretto:17 AS Builder

ENV JWT_SECRET=dummy
ENV RDS_USERNAME=dummy
ENV RDS_PASSWORD=dummy

WORKDIR /app

COPY gradlew build.gradle settings.gradle /app/
COPY gradle /app/gradle

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY . .

RUN ./gradlew clean build -x test --no-daemon

# run stage
FROM amazoncorretto:17

WORKDIR /app

EXPOSE 8080

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]