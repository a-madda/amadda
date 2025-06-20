package com.seungse.amadda.domain;

import com.seungse.amadda.adapter.out.persistance.entity.ChatMessageEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class ChatRoom implements Serializable {

    private String roomId;
    private String name;
    private List<ChatMessageEntity> chats;

    public static ChatRoom create(String name) {
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .name(name)
                .build();
    }

}