package com.example.demo.controller;

import com.example.demo.entity.Orders;
import com.example.demo.service.OrderOriginalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("kafka")
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OrderOriginalService orderOriginalService;
    private long start = 0, start2 = 0;
    private long original_time = 0;
    private long kafka_time = 0;


    @GetMapping("kafka")
    public String kafka(){
        return "kafka/kafka";
    }

    @GetMapping("order")
    public String order(){
        return "kafka/order";
    }

    @PostMapping("/send")
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        long startTime = System.currentTimeMillis();
        start++;

        try {
            String message = "itemName- " + order.getItemName() + ", quantity- " + order.getQuantity();


            /// ////////////////////////////////////////////////////////////
            kafkaTemplate.send("test-topic", message);
            /// ////////////////////////////////////////////////////////////
            long endTime = System.currentTimeMillis();

            System.out.println("================> 메시지 처리 시간: " + (endTime - startTime) + "ms");
            kafka_time += endTime - startTime;

            System.out.println(start + ">> kafka_time ============>> " + kafka_time);
            return ResponseEntity.ok("Order sent to Kafka: " + message);

        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            System.out.println("================> 에러 발생, 처리 시간: " + (endTime - startTime) + "ms");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing order");
        }
    }

    @PostMapping("/send_original")
    public ResponseEntity<String> createOrder_before(@RequestBody Orders order) {
        long startTime = System.currentTimeMillis();
        start2++;
        try {

            String message = "itemName- " + order.getItemName() + ", quantity- " + order.getQuantity();

            orderOriginalService.processOrder(order);

            long endTime = System.currentTimeMillis();
            System.out.println("================> 메시지 처리 시간: " + (endTime - startTime) + "ms");
            original_time += endTime - startTime;
            System.out.println("start2 + \">> original_time ============>> " + original_time);
            return ResponseEntity.ok("Order sent to original: " + message);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            System.out.println("================> 에러 발생, 처리 시간: " + (endTime - startTime) + "ms");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing order");
        }
    }


}
