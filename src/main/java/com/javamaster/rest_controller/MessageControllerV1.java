package com.javamaster.rest_controller;

import com.javamaster.model.Message;
import com.javamaster.service.impl.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageControllerV1 {
    private final ProducerService producerService;

    @Autowired
    public MessageControllerV1(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/generate")
    @ResponseStatus(HttpStatus.OK)
    public Message generate(@RequestBody Message message) {
        return producerService.produce(message);
    }
}
