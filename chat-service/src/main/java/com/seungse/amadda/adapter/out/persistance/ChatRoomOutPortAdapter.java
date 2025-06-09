package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.adapter.out.persistance.entity.ChatRoomEntity;
import com.seungse.amadda.adapter.out.persistance.entity.ChatType;
import com.seungse.amadda.adapter.out.persistance.entity.Participant;
import com.seungse.amadda.adapter.out.persistance.repository.ChatRoomPostgresRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ChatRoomOutPortAdapter {

    private final ChatRoomPostgresRepository chatRoomPostgresRepository;

    public void createChatRoom(String description, ChatType chatType, Long userId) {
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.builder()
            .description(description)
            .chatType(chatType)
            .createdBy(userId)
            .createdAt(LocalDateTime.now())
            .build();
        chatRoomPostgresRepository.save(chatRoomEntity);
    }

    public void enterChatRoom(Long roomId, Long userId, String nickname) {
        ChatRoomEntity chatRoomEntity = chatRoomPostgresRepository.findById(roomId)
            .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));
        chatRoomEntity.addParticipant(Participant.of(userId, nickname, chatRoomEntity));
        chatRoomPostgresRepository.save(chatRoomEntity);
    }

    public void sendMessage(Long roomId, String message, Long sender) {
        ChatRoomEntity chatRoomEntity = chatRoomPostgresRepository.findById(roomId)
            .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));
        chatRoomEntity.addMessage(message, sender);
        chatRoomPostgresRepository.save(chatRoomEntity);
    }

}
