FROM openjdk:17
LABEL author="Roman Gromov"
ADD build/libs/Authorization-0.0.1-SNAPSHOT.jar app.jar
ADD authorizationDb.sqlite authorizationDb.sqlite
ADD dbScripts.sql dbScripts.sql
CMD ["java", "-jar", "/app.jar"]