package com.example.QuizApp.controllers;

import com.example.QuizApp.DTO.FriendRequestDTO;
import com.example.QuizApp.DTO.RespondRequestDTO;
import com.example.QuizApp.models.Friendship;
import com.example.QuizApp.models.FriendshipStatus;
import com.example.QuizApp.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {
    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/{userId}")
    public List<Friendship> getFriends(@PathVariable Long userId) {
        return friendshipService.getFriends(userId);
    }

    @PostMapping("/send-request")
    public void sendFriendRequest(@RequestBody FriendRequestDTO request) {
        Long userId = request.getUserId();
        Long friendId = request.getFriendId();
        friendshipService.sendFriendRequest(userId, friendId);
    }

    @PostMapping("/respond-request")
    public void respondToFriendRequest(@RequestBody RespondRequestDTO request) {
        Long friendshipId = request.getFriendshipId();
        String status = request.getStatus();
        FriendshipStatus friendshipStatus = FriendshipStatus.valueOf(status.toUpperCase());
        friendshipService.respondToRequest(friendshipId, friendshipStatus);
    }
}
