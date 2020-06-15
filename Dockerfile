FROM maven:3.6.3-openjdk-15

# COPY . /usr/src/mymaven
COPY pom.xml /usr/app/
WORKDIR /usr/app
RUN mvn clean install