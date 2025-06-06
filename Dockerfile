#FROM maven:3.9.9-amazoncorretto-24
FROM maven:3.8.5-openjdk-18

COPY . /app
WORKDIR /app

RUN mvn clean && mvn install 
ENTRYPOINT ["/bin/bash"]