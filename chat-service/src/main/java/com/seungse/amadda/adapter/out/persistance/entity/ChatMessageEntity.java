package com.seungse.amadda.adapter.out.persistance.entity;

import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.generator.IdGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@Table(name = "chat_message")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor(access =  AccessLevel.PROTECTED)
public class ChatMessageEntity {

    @Id
    @IdGenerator
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoomEntity chatRoom;

    /**
     * 채팅 메시지 내용
     */
    private String content;

    /**
     * 채팅 메시지 작성자 ID
     */
    private Long senderId;

    /**
     * 채팅 메시지 작성 시간
     */
    private LocalDateTime sentAt;

    public ChatMessage toChatMessage() {
        return ChatMessage.builder()
                .id(id)
                .roomId(chatRoom.getRoomId().toString())
                .senderId(senderId)
                .message(content)
                .sentAt(sentAt.toString())
                .build();
    }
}
