package com.seungse.amadda.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private LocalDateTime sentAt;

    public void setSentTime() {
        this.sentAt = LocalDateTime.now();
    }

}
