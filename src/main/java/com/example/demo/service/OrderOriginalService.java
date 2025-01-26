package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderOriginalService {

    private final OrderRepository orderRepository;

    public void processOrder(Orders order) {
        System.out.println("Received order: " + order);
        // DB에 저장
        orderRepository.save(order);

        System.out.println("Order saved to DB: " + order);
    }
}