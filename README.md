# Fake Data Generator

This project provide a simple interface to generate various streams of data for testing and experimentation purposes.

## Table of Contents

- [Introduction](#introduction)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)


## Introduction

When I first started learning about more modern (Java 8) ways of working with data I missed a sandbox environment to 
test functionality beyond the trivial stuff. The idea behind this project is to provide different data sources.

DataFaker library is utilized to generate different kinds of POJOs that look real, but are not. The data can be streamed
as SSE, published to a message broker or fetched as raw JSON.

## Requirements

- Java 22

## Installation

The application has two profiles available **_rabbit_** and **_kafka_**.

To run the application with rabbit:
```shell script
/mvnw spring-boot:run -Dspring-boot.run.profiles=rabbit
```

## Usage

### REST
#### Get list of people 

```shell script
curl http://localhost:8080/get/people/limit/10  
```
### SSE
#### Stream unlimited amount of people with time interval of 1000ms
```shell script
curl -s -H "Accept:text/event-stream" http://localhost:8080/stream/people/unlimited/interval/1000  
```

#### Stream 10 people with time interval of 1000ms
```shell script
curl -s -H "Accept:text/event-stream" http://localhost:8080/stream/people/limit/10/interval/1000
```
### RabbitMQ
#### Publish unlimited amount of people with time interval of 1000ms
```shell script
curl -s -H "Accept:text/event-stream" http://localhost:8080/publish/people/unlimited/interval/1000  
```
#### Publish 10 people with time interval of 1000ms
```shell script
curl -s -H "Accept:text/event-stream" http://localhost:8080/publish/people/limit/10/interval/1000
```

### Swagger UI
[localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

