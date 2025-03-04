package com.poc.eventsourcing.eventsourcing.helloworld;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    private final HelloService helloService;
    private final HelloConsumer helloConsumer;

    public HelloController(HelloService helloService, HelloConsumer helloConsumer) {
        this.helloService = helloService;
        this.helloConsumer = helloConsumer;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String topic, @RequestBody HelloWorld message) {
        try {
            helloService.sendMessage(topic, message);
            return String.format("Message sent to Kafka topic: %s", topic);
        } catch (Exception e) {
            return String.format("Error send message %s", e.getMessage());
        }
    }

    @GetMapping("/message")
    public List<HelloWorld> getMessage(@RequestParam String key) {
        return helloConsumer.getMessage(key);
    }
}
