#----Build-With-Tests----#
FROM gradle:8-jdk17 AS gradle-build

WORKDIR /app

COPY . .

RUN gradle clean build

#----Start----#
FROM openjdk:17 AS backend-realese

WORKDIR /app

COPY --from=gradle-build /app/build/libs/bst-backend.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]

