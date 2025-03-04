package com.poc.eventsourcing.eventsourcing.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HelloWorld {
    private String key;
    private List<String> contents;
}