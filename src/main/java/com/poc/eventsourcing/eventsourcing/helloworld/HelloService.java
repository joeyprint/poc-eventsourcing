package com.poc.eventsourcing.eventsourcing.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloService {
    Logger logger = LoggerFactory.getLogger(HelloService.class);
    private KafkaTemplate<String, HelloWorld> kafkaTemplate;

    public HelloService(KafkaTemplate<String, HelloWorld> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, HelloWorld message) {
        kafkaTemplate.send(topic, message);
        logger.info("Produced message: {}", message.getContents());
    }
}
