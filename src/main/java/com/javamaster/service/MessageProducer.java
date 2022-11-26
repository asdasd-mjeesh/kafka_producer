package com.javamaster.service;

import com.javamaster.model.Message;

public interface MessageProducer {

    Message produce(Message message);
}
