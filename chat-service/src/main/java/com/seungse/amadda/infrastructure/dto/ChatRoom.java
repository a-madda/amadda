package com.seungse.amadda.infrastructure.dto;

import lombok.Getter;

@Getter
public class ChatRoom {

    private String roomId;
    private String name;
    private Long ownerId;

    public static ChatRoom of(String roomId, String name, Long ownerId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = roomId;
        chatRoom.name = name;
        chatRoom.ownerId = ownerId;
        return chatRoom;
    }

}
