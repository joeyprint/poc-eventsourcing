## PoC Event Sourcing with Kafka

### Prerequisites

- Java Version 17 (Can use SDKMAN for Java Management - [Website](https://sdkman.io/))
- Maven
- Kafka [Quickstart](https://kafka.apache.org/quickstart)


### Development

**Install and start Kafka in local**

```bash
docker pull apache/kafka:3.9.0
docker run -p 9092:9092 apache/kafka:3.9.0
```

**How to test the project**

Run Spring Boot application

```shell
mvn spring-boot:run
```

Send a message via REST API

```shell
curl --location --request POST 'http://localhost:8080/send?topic=translator&message=translateMessage'
# or
curl --location --request POST 'http://localhost:8080/send?topic=kafka&message=HelloWorld'
```

Expect result

```yaml
Produced message: translateMessage
Consumed topic message: translator
Consumed value message: translateMessage
```