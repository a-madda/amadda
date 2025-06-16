package com.seungse.amadda.adapter.out.persistance.entity;

import com.seungse.amadda.generator.IdGenerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 참여자
 * 참여자는 채팅방에 속하며, 사용자 ID와 닉네임을 가집니다.
 * @author seunggu.lee
 * @since 1.0
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant {

    @Id
    @IdGenerator
    private Long id;

    private Long userId;

    private String nickname;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoomEntity chatRoom;

    public static Participant of(Long userId, String nickname, ChatRoomEntity chatRoomEntity) {
        Participant participant = new Participant();
        participant.userId = userId;
        participant.chatRoom = chatRoomEntity;
        participant.nickname = nickname;
        return participant;
    }

    public void enterChatRoom(ChatRoomEntity chatRoom) {
        this.chatRoom = chatRoom;
    }

}