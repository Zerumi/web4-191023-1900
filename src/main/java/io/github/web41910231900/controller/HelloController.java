package io.github.web41910231900.controller;

import io.github.web41910231900.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @GetMapping
    public Greeting printHello(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
