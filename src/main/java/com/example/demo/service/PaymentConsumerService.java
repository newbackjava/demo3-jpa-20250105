package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "payment-group")
    public void processPayment(String orderDetails) {
        System.out.println("payment for order===========: " + orderDetails);
        // 결제 처리 로직 추가 가능
    }
}