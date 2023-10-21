package io.github.web41910231900.controller;

import io.github.web41910231900.model.response.PostResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public PostResponse printHello() {
        var response = new PostResponse();
        response.setMessage("girls sometimes may be beautiful...");
        return response;
    }
}
