package com.example.QuizApp.controllers;

import com.example.QuizApp.models.Message;
import com.example.QuizApp.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.sendMessage") // Client sends message here
    @SendTo("/topic/messages") // Broadcast message to all subscribers
    public Message sendMessage(Message chatMessage) {
        chatMessage.setTimestamp(System.currentTimeMillis());
        return chatMessage; // Return message to be broadcast
    }

    @GetMapping("/messages")
    public List<Message> getMessages(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        return chatService.getMessages(user1Id, user2Id);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam Long senderId, @RequestParam Long recipientId, @RequestParam String content) {
        chatService.sendMessage(senderId, recipientId, content);
    }
}
