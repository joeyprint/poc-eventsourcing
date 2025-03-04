package com.poc.eventsourcing.eventsourcing.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorld {
    private String key;
    private List<String> contents;
}