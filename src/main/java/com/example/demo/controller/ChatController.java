package com.example.demo.controller;

import com.example.demo.entity.Chat;
import com.example.demo.entity.Message;
import com.example.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Value
@Controller
@RequiredArgsConstructor
public class ChatController {

    ChatService chatService;

    // -------------------- chatting process -----------------
    @MessageMapping("/chatting") // 클라이언트가 "/app/sendMessage"로 보낸 메시지를 처리
    @SendTo("/topic/chatting")     // 구독한 클라이언트에게 "/topic/messages"로 전달
    public Message sendMessage(@RequestBody Message message) throws Exception {
        System.out.println("message >>> " + message);
        Date date = new Date();
        message.setTime(date.getYear() + 1900 + ":" + date.getHours() + ":" + date.getMinutes());
        return message;
    }

    // -------------------- chatbot process -----------------
    @MessageMapping("/chatbot") // 클라이언트에서 "/app/chatbot"로 보낸 메시지 처리
    @SendTo("/topic/chatbot") // "/topic/chatbot"로 메시지 전달
    public Chat chatbot(@RequestBody Chat chat) {
        System.out.println("chatVO >>> " + chat);
        return chatService.saveMessage(chat);
    }
    
    // -------------------- html call -----------------
    @GetMapping("chat/chat")
    public String chat() {
        return "chat/chat";
    }

    @GetMapping("chat/chat2")
    public String chat2() {
        return "chat/chat2";
    }

    @GetMapping("chat/chatting")
    public String chatting() {
        return "chat/chatting";
    }
    // -------------------- html call -----------------
}
