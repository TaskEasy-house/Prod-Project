package com.taskEasy.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class healthController {
    @GetMapping("/health")
    public String healthCheck(){
        return "Backend is successfully integrated with Frontend!";
    }
}
