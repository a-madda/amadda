package com.seungse.amadda.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@ToString
@Builder
public class ChatMessage {

    /**
     * 메시지 ID
     */
    private Long id;
    /**
     * 메시지 유형
     */
    private MessageType type;
    /**
     * 방 ID
     */
    private String roomId;
    /**
     * 발신자 ID
     */
    private Long senderId;
    /**
     * 발신자
     * 임시로 만든 필드로, 추후에는 senderId를 통해 cache 에서 조회 예정
     */
    @Deprecated
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
        this.sentAt = LocalDateTime.now().toString();
    }

}
