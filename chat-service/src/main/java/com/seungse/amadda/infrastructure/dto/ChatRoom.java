package com.seungse.amadda.infrastructure.dto;

import com.seungse.amadda.domain.ChatType;
import lombok.Getter;

@Getter
public class ChatRoom {

    private String roomId;
    private String name;
    private ChatType chatType;
    private Long ownerId;

    public static ChatRoom of(String roomId, String name, ChatType chatType, Long ownerId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = roomId;
        chatRoom.name = name;
        chatRoom.chatType = chatType;
        chatRoom.ownerId = ownerId;
        return chatRoom;
    }

}
