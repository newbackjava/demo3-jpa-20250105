package com.example.demo.service;

import com.example.demo.entity.UserAction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActionProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendUserAction(UserAction userAction) {
        String message = String.format("userId:%s, action:%s, timestamp:%d",
                userAction.getUserId(), userAction.getAction(),
                System.currentTimeMillis());
        kafkaTemplate.send("user-actions", message);
        System.out.println("User action sent: " + message);
    }
}