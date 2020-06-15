FROM maven:3.6.3-openjdk-15

# COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install