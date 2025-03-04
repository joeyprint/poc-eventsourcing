package com.poc.eventsourcing.eventsourcing.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    Logger logger = LoggerFactory.getLogger(HelloService.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public HelloService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        logger.info(String.format("Produced message: %s", message));
    }
}
