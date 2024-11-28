package com.example.QuizApp.services;

import com.example.QuizApp.models.Message;
import com.example.QuizApp.models.User;
import com.example.QuizApp.models.data.ConversationRepository;
import com.example.QuizApp.models.data.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    public List<Message> getMessages(Long userId1, Long userId2) {
        return messageRepository.findBySenderIdAndRecipientId(userId1, userId2);
    }

    public void sendMessage(Long senderId, Long recipientId, String content) {
        Message message = new Message();
        message.setSender(new User(senderId));
        message.setRecipient(new User(recipientId));
        message.setContent(content);
        messageRepository.save(message);
    }
}
