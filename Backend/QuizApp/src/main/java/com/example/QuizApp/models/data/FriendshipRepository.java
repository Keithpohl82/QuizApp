package com.example.QuizApp.models.data;

import com.example.QuizApp.models.Friendship;
import com.example.QuizApp.models.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUserId(Long userId);
    List<Friendship> findByFriendIdAndStatus(Long friendId, FriendshipStatus status);
}
