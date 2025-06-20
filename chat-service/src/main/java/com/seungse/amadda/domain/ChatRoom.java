package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class ChatRoom implements Serializable {

    @Serial
    private static final long serialVersionUID = -4388848801091940221L;

    private String roomId;
    private String name;
    private ChatType chatType;
    private List<ChatMessage> chats;

    public static ChatRoom create(String name, ChatType chatType) {
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .chatType(chatType)
                .name(name)
                .build();
    }

    public void addChat(String roomId, Long senderId, String message, String sentAt) {
        ChatMessage chatMessage = ChatMessage.builder()
                .roomId(roomId)
                .senderId(senderId)
                .message(message)
                .sentAt(sentAt)
                .build();
        if (this.chats == null) {
            this.chats = new ArrayList<>();
        }
        this.chats.add(chatMessage);
    }

}