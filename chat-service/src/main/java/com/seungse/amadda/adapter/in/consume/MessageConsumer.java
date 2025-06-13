package com.seungse.amadda.adapter.in.consume;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungse.amadda.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic.chat}", groupId = "${spring.kafka.topic.group}")
    public void consumeMessage(String message) {
        try {
            ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);
            log.info("Received message: {}", chatMessage);
        } catch (Exception e) {
            // Handle deserialization or processing errors
            e.printStackTrace();
        }
    }
}
