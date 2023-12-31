package io.github.web41910231900.controller;

import io.github.web41910231900.model.response.PostResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    @CrossOrigin
    public ResponseEntity<PostResponseDTO> printHello() {
        final var response = new PostResponseDTO();
        response.setMessage("girls sometimes may be beautiful...");
        return ResponseEntity.ok(response);
    }
}
