package com.hashing.url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "Tracking";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void addMessageToTopic(String message){
        this.kafkaTemplate.send(TOPIC, message);
    }
}
