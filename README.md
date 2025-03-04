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
curl --location 'http://localhost:8080/send?topic=translator' \
--header 'Content-Type: application/json' \
--data '{
  "key": "Translate-1",
  "contents": [
    "item 1",
    "item 2",
    "item 3",
    "item 4",
    "item 5"
  ]
}'
```

Expect result in log console after request send message

```yaml
Produced message: [item 1, item 2, item 3]
Consumed topic message: translator
Consumed value message: [item 1, item 2, item 3]
```

Get a message via REST API

```shell
curl --location 'localhost:8080/message?key=Translate-1'
```