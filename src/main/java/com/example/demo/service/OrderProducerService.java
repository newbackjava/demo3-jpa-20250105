package com.example.demo.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(String orderDetails) {
        kafkaTemplate.send("test-topic", orderDetails);
        System.out.println("Order sent to Kafka: " + orderDetails);
    }
}