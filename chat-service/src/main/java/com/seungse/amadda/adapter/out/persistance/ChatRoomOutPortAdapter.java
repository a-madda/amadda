package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.adapter.out.persistance.entity.ChatRoomEntity;
import com.seungse.amadda.adapter.out.persistance.entity.Participant;
import com.seungse.amadda.adapter.out.persistance.repository.ChatRoomPostgresRepository;
import com.seungse.amadda.application.port.out.ChatRoomSaveOutPort;
import com.seungse.amadda.application.port.out.ChatRoomSearchOutPort;
import com.seungse.amadda.domain.ChatRoom;
import com.seungse.amadda.domain.ChatType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatRoomOutPortAdapter implements ChatRoomSaveOutPort, ChatRoomSearchOutPort {

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

    @Transactional
    public void sendMessage(ChatRoom chatRoom) {
        ChatRoomEntity chatRoomEntity = chatRoomPostgresRepository.findByRoomId(UUID.fromString(chatRoom.getRoomId()))
            .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));
        chatRoomEntity.addMessage(chatRoom.getChats().getLast());
        chatRoomPostgresRepository.save(chatRoomEntity);
    }

    @Override
    public ChatRoom saveChatRoom(ChatRoom chatRoom, Long ownerId) {
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.builder()
                .roomId(UUID.fromString(chatRoom.getRoomId()))
                .name(chatRoom.getName())
                .chatType(chatRoom.getChatType())
                .createdBy(ownerId)
                .createdAt(LocalDateTime.now())
                .build();
        ChatRoomEntity savedEntity = chatRoomPostgresRepository.save(chatRoomEntity);
        return ChatRoom.builder()
                .roomId(savedEntity.getRoomId().toString())
                .name(savedEntity.getName())
                .build();
    }

    @Override
    public boolean existsChatRoom(UUID roomId) {
        return chatRoomPostgresRepository.existsByRoomId(roomId);
    }

    @Override
    public Optional<ChatRoom> findChatRoomById(UUID roomId) {
        Optional<ChatRoomEntity> byRoomId = chatRoomPostgresRepository.findByRoomId(roomId);
        if (byRoomId.isPresent()) {
            ChatRoomEntity chatRoomEntity = byRoomId.get();
            return Optional.of(ChatRoom.builder()
                    .roomId(chatRoomEntity.getRoomId().toString())
                    .name(chatRoomEntity.getName())
                    .chatType(chatRoomEntity.getChatType())
                    .chats(chatRoomEntity.getMessages().stream()
                            .map(message -> message.toChatMessage())
                            .toList())
                    .build());
        }
        return Optional.empty();
    }
}
