package com.poc.eventsourcing.eventsourcing.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String sendMessage(@RequestParam String topic, @RequestParam String message) {
        helloService.sendMessage(topic, message);
        return String.format("Message sent to Kafka topic: %s", topic);
    }

    @GetMapping("/message")
    public String getMessage() {
        return String.format("Message: %s",  helloConsumer.getMessage());
    }
}
