FROM openjdk:17
LABEL author="Roman Gromov"
ADD build/libs/Orders-0.0.1-SNAPSHOT.jar app.jar
ADD ordersDb.sqlite ordersDb.sqlite
ADD dbScripts.sql dbScripts.sql
CMD ["java", "-jar", "/app.jar"]