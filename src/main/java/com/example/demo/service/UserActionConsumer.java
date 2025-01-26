package com.example.demo.service;

import com.example.demo.entity.UserAction;
import com.example.demo.repository.UserActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActionConsumer {
    private final UserActionRepository userActionRepository;

    @KafkaListener(topics = "user-actions", groupId = "analytics-group")
    public void consume(String message) {
        System.out.println("Received user action: " + message);

        String[] parts = message.split(", ");
        String userId = parts[0].split(":")[1];
        String action = parts[1].split(":")[1];
        long timestamp = Long.parseLong(parts[2].split(":")[1]);

        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setAction(action);
        userAction.setTimestamp(timestamp);

        userActionRepository.save(userAction);
        System.out.println("User action saved: " + message);
    }
}