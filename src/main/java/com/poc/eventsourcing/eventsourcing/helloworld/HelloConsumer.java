package com.poc.eventsourcing.eventsourcing.helloworld;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class HelloConsumer {
    Logger logger = LoggerFactory.getLogger(HelloConsumer.class);
    private final List<HelloWorld> messages = new ArrayList<>(); // Store messages in memory

    @KafkaListener(topics = "translator", groupId = "poc-kafka")
    public void consume(ConsumerRecord<String, HelloWorld> record) {
        try {
            logger.info(String.format("Consumed topic message: %s", record.topic()));
            logger.info("Consumed value message: {}", record.value().getContents());
            synchronized (messages) {
                HelloWorld content = new HelloWorld(record.value().getKey(), record.value().getContents());
                messages.add(content);
            }
        } catch (Exception e) {
            logger.error("Error listening consume message: {}", e.getMessage());
        }
    }

    public List<HelloWorld> getMessage(String key) {
        synchronized (messages) {
            List<HelloWorld> helloMessage = messages.stream().filter(msg -> msg.getKey().equals(key)).toList();
            return helloMessage;
        }
    }
}
