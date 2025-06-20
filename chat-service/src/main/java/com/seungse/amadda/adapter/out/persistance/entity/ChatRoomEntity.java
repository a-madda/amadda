package com.seungse.amadda.adapter.out.persistance.entity;

import com.seungse.amadda.domain.ChatType;
import com.seungse.amadda.generator.IdGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Builder
@Table(name = "chat_room")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoomEntity {

    @Id
    @IdGenerator
    private Long id;

    @Column(unique = true)
    private String roomId;

    /**
     * 방 제목 ( 1:1 채팅방의 경우 상대방의 이름 )
     */
    private String name;
    /**
     * 방 유형 ( PRIVATE: 1:1 , GROUP: 그룹 )
     */
    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    /**
     * 방 참여자 수
     */
    private Integer participantCount;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Participant> participants;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageEntity> messages;

    /**
     * 방 설명
     */
    private String description;
    private Long createdBy;
    private LocalDateTime createdAt;

    public void addParticipant(Participant participant) {
        if (participants == null) {
            participants = Set.of();
        }
        participants.add(participant);
        participant.enterChatRoom(this);
    }

    public void addMessage(String message, Long sender) {
        ChatMessageEntity chatMessageEntity = ChatMessageEntity.builder()
                .content(message)
                .senderId(sender)
                .sentAt(LocalDateTime.now())
                .chatRoom(this)
                .build();
        if (messages == null) {
            messages = List.of();
        }
        messages.add(chatMessageEntity);

    }

}
