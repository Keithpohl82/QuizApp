package com.example.QuizApp.DTO;

public class RespondRequestDTO {
    private Long friendshipId;
    private String status;

    public Long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
