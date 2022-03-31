
FROM maven:3.8.4-openjdk-17 as compiler

ENV WEBAPP_VERSION=lastest

WORKDIR /usr/share/app

COPY backend/pom.xml /usr/share/app/pom.xml
RUN mvn clean compile && mvn -B -f pom.xml dependency:resolve
COPY backend/src /usr/share/app/src
RUN mvn package

FROM eclipse-temurin:17-jre-alpine

ENV PORT 8443
# ENV SPRING_DATASOURCE_URL jdbc:postgresql://postgres/softwear

WORKDIR /usr/share/app

COPY --from=compiler /usr/share/app/target/webapp5*.jar /usr/share/app/webapp5.jar

EXPOSE 8443

CMD ["java", "-jar", "webapp5.jar"]

