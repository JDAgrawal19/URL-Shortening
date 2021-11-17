package com.hashing.url.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumeService {

    @KafkaListener(topics = "Tracking", groupId = "group_id")
    public void processmessage(String message)throws IOException {
        //process message for event tracking
    }
}
