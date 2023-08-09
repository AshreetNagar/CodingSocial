package com.codingSocial.codingSocial.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
@RequestMapping("api/")

public class helloController {
    @GetMapping("/hello")
    public ResponseEntity<?> getHello() {
        return ResponseEntity.ok().body("hello");
    } 

}
