FROM maven:3.6.0-jdk-11-slim AS build
## Copy src and pom
COPY src /home/app/src
COPY pom.xml /home/app/

##RUN mkdir -p /root/.m2 \
  ##  && mkdir /root/.m2/repository
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/springbootapp-0.0.1-SNAPSHOT.jar /usr/local/lib/springbootapp.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/usr/local/lib/springbootapp.jar"]