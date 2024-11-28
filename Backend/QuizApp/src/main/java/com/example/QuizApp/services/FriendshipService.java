package com.example.QuizApp.services;

import com.example.QuizApp.models.Friendship;
import com.example.QuizApp.models.FriendshipStatus;
import com.example.QuizApp.models.User;
import com.example.QuizApp.models.data.FriendshipRepository;
import com.example.QuizApp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Friendship> getFriends(Long userId) {
        return friendshipRepository.findByUserId(userId);
    }

    public void sendFriendRequest(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Friend not found"));

        // Create the Friendship object
        Friendship friendship = new Friendship();
        friendship.setUser(user);   // Set the user (this should already be persisted)
        friendship.setFriend(friend);  // Set the friend (this should also be persisted)
        friendship.setStatus(FriendshipStatus.PENDING);

        // Save the friendship
        friendshipRepository.save(friendship);
    }

    public void respondToRequest(Long friendshipId, FriendshipStatus status) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow();
        friendship.setStatus(status);
        friendshipRepository.save(friendship);
    }
}
