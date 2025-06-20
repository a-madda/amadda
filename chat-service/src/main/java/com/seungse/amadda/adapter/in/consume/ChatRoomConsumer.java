package com.seungse.amadda.adapter.in.consume;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungse.amadda.application.port.in.ChatRoomSaveUsecase;
import com.seungse.amadda.infrastructure.dto.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatRoomConsumer {

    private final ObjectMapper objectMapper;
    private final ChatRoomSaveUsecase chatRoomSaveUsecase;

    @KafkaListener(topics = "${spring.kafka.topic.chat-room}")
    public void consumeMessage(String message) {
        try {
            // Assuming the message is a JSON string representing a ChatRoom object
            ChatRoom chatRoom = objectMapper.readValue(message, ChatRoom.class);
            log.info("Received room message: {}", chatRoom);
            com.seungse.amadda.domain.ChatRoom savedChatRoom = chatRoomSaveUsecase.saveChatRoom(chatRoom.getRoomId(), chatRoom.getName(), chatRoom.getOwnerId());
            log.debug("created chat room: {}", savedChatRoom);
        } catch (Exception e) {
            // Handle deserialization or processing errors
            log.error("Error processing room message: {}", e.getMessage(), e);
        }
    }

}
