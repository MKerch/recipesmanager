FROM openjdk:11.0.7-jdk
ADD target/recipesmanager-*.jar /opt/recipesmanager.jar

WORKDIR /opt

ENTRYPOINT java -jar /opt/recipesmanager.jar