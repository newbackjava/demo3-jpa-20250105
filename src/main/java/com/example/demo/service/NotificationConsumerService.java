package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "notification-group")
    public void sendNotification(String orderDetails) {
        System.out.println("Notification sent for order============: " + orderDetails);
        // 알림 전송 로직 추가 가능
    }
}