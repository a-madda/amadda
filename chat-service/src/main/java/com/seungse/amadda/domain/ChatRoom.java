package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public String getEnterMessage(String sender) {
        return sender + "님이 입장했습니다.";
    }

    public String getExitMessage(String sender) {
        return sender + "님이 퇴장했습니다.";
    }
}