package com.example.QuizApp.models.data;

import com.example.QuizApp.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Conversation findByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}
