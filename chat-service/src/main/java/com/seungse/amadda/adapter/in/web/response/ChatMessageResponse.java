package com.seungse.amadda.adapter.in.web.response;

import lombok.Getter;

@Getter
public class ChatMessageResponse {

    private Long id;
    private String content;
    private Long senderId;
    private String sentAt;

    public ChatMessageResponse(Long id, String message, Long senderId, String string) {
        this.id = id;
        this.content = message;
        this.senderId = senderId;
        this.sentAt = string;
    }
}
