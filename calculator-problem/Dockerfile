FROM openjdk:11
EXPOSE 5055
ADD target/calculator-problem.jar calculator-problem.jar
ENTRYPOINT ["java","-jar","/calculator-problem.jar"]

# docker-compose up