FROM maven:3.5.4-jdk-7

RUN mkdir -p /app
WORKDIR /app

COPY . /app
