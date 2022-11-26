package com.javamaster.service.impl;

import com.javamaster.model.Message;
import com.javamaster.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService implements MessageProducer {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Message produce(Message message) {
        try {
            var future = kafkaTemplate.send("message", message);
            future.thenAccept(System.out::println);
            var result = future.get();
            return result.getProducerRecord().value();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
