package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ChatRoom {
    private String roomId;
    private String name;

    public static ChatRoom create(String name) {
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .name(name)
                .build();
    }

}