package com.javamaster.rest_controller;

import com.javamaster.model.Message;
import com.javamaster.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller/api/v1")
public class ControllerV1 {
    private final ProducerService producerService;

    @Autowired
    public ControllerV1(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/generate")
    public String generate(@RequestBody Message message) {
        producerService.produce(message);
        return "OK";
    }
}
