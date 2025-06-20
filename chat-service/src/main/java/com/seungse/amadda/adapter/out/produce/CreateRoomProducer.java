package com.seungse.amadda.adapter.out.produce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungse.amadda.domain.ChatType;
import com.seungse.amadda.infrastructure.dto.ChatRoom;
import com.seungse.amadda.infrastructure.kafka.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class CreateRoomProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;
    private final ObjectMapper objectMapper;

    public void produce(String roomId, String name, ChatType chatType, Long userId) throws JsonProcessingException {
            String message = objectMapper.writeValueAsString(ChatRoom.of(roomId, name, chatType, userId));
        try {
            kafkaTemplate.send(kafkaTopicProperties.getChatRoom(), message).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
