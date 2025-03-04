package com.poc.eventsourcing.eventsourcing.helloworld;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloConsumer {
    Logger logger = LoggerFactory.getLogger(HelloConsumer.class);
    private final List<String> messages = new ArrayList<>(); // Store messages in memory

    @KafkaListener(topics = "kafka", groupId = "poc-kafka")
    public void consume(ConsumerRecord<String, String> record) {
        logger.info(String.format("Consumed topic message: %s", record.topic()));
        logger.info(String.format("Consumed value message: %s", record.value()));
        synchronized (messages) {
            messages.add(record.value());
        }
    }

    @KafkaListener(topics = "translator", groupId = "poc-kafka")
    public void consume2(ConsumerRecord<String, String> record) {
        logger.info(String.format("Consumed topic message: %s", record.topic()));
        logger.info(String.format("Consumed value message: %s", record.value()));
        synchronized (messages) {
            messages.add(record.value());
        }
    }

    public List<String> getMessage() {
        synchronized (messages) {
            return new ArrayList<>(messages);
        }
    }
}
