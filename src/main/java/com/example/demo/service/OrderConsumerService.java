package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumerService {

    private final OrderRepository orderRepository;
    private long kafka_time = 0;
    private long kafka_count = 0;

    @KafkaListener(topics = "test-topic", groupId = "order-group")
    public void processOrder(String message) {
        long startTime = System.currentTimeMillis();
        kafka_count++;

        System.out.println("Received order: " + message);

        // 예: "itemName: Laptop, quantity: 2"
        String[] parts = message.split(", ");
        String itemName = parts[0].split("- ")[1];
        int quantity = Integer.parseInt(parts[1].split("- ")[1]);

        // DB에 저장
        Orders order = new Orders();
        order.setItemName("*" + itemName);
        order.setQuantity(quantity);

        /// ############## db처리 #############
        orderRepository.save(order);
        System.out.println(kafka_count + ">> 받은 order db에 저장됨.");
        /// ############## db처리 #############

        long endTime = System.currentTimeMillis();
        System.out.println("================> 메시지 처리 시간: " + (endTime - startTime) + "ms");
        kafka_time += endTime - startTime;

        System.out.println(kafka_count + ">> kafka_time ============>> " + kafka_time);
        System.out.println("Order saved to DB: " + message);
    }
}