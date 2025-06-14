package com.seungse.amadda.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@ToString
public class ChatMessage {

    /**
     * 메시지 유형
     */
    private MessageType type;
    /**
     * 방 ID
     */
    private String roomId;
    /**
     * 발신자
     */
    private String sender;
    /**
     * 메시지 내용
     */
    private String message;


    /**
     * 메시지 전송 시간
     */
    private String sentAt;

    public void setSentTime() {
        this.sentAt = Instant.now().toString();
    }

}
