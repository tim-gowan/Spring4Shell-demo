#Build Target folder
FROM maven:3.8.4-jdk-11 as build
#WORKDIR /target
#COPY pom.xml /target
#COPY target /target
#COPY . /target
#RUN mvn clean package


WORKDIR /app
COPY pom.xml /app
COPY . /app
RUN mvn clean -f /app/pom.xml package


#Initialize Tomcat
FROM tomcat:9.0-slim
#COPY flag /flag
EXPOSE 8080
COPY --from=build /app/target/springmvc5-helloworld-example-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps

ENTRYPOINT ["catalina.sh", "run"]
