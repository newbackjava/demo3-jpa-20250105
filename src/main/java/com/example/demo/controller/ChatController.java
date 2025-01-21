package com.example.demo.controller;

import com.example.demo.entity.Chat;
import com.example.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Value
@Controller
@RequiredArgsConstructor
public class ChatController {

    ChatService chatService;

    @MessageMapping("/sendMessage") // 클라이언트가 "/app/sendMessage"로 보낸 메시지를 처리
    @SendTo("/topic/messages")     // 구독한 클라이언트에게 "/topic/messages"로 전달
    public Chat sendMessage(@RequestBody Chat chat) {

        System.out.println("chatVO >>> " + chat);
        //return messageRepository.save(message); // 저장 후 반환
        return chatService.saveMessage(chat);
    }

    @MessageMapping("/chatbot") // 클라이언트에서 "/app/sendMessage"로 보낸 메시지 처리
    @SendTo("/topic/chatbot") // "/topic/messages"로 메시지 전달
    public String chatbot(String message) {
        return message; // 메시지를 그대로 반환
    }

    @GetMapping("chat/chat")
    public String chat() {
        return "chat/chat";
    }
}
