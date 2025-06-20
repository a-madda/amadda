package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.adapter.out.persistance.entity.ChatRoomEntity;
import com.seungse.amadda.adapter.out.persistance.entity.Participant;
import com.seungse.amadda.adapter.out.persistance.repository.ChatRoomPostgresRepository;
import com.seungse.amadda.application.port.out.ChatRoomSaveOutPort;
import com.seungse.amadda.domain.ChatRoom;
import com.seungse.amadda.domain.ChatType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ChatRoomOutPortAdapter implements ChatRoomSaveOutPort {

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

    @Override
    public ChatRoom saveChatRoom(ChatRoom chatRoom, Long ownerId) {
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.builder()
                .roomId(chatRoom.getRoomId())
                .name(chatRoom.getName())
                .chatType(chatRoom.getChatType())
                .createdBy(ownerId)
                .createdAt(LocalDateTime.now())
                .build();
        ChatRoomEntity savedEntity = chatRoomPostgresRepository.save(chatRoomEntity);
        return ChatRoom.builder()
                .roomId(savedEntity.getRoomId())
                .name(savedEntity.getName())
                .build();
    }
}
