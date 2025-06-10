package com.seungse.amadda.adapter.out.produce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.infrastructure.kafka.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;
    private final ObjectMapper objectMapper;

    public void produce(ChatMessage chatMessage) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(chatMessage);
        try {
            kafkaTemplate.send(kafkaTopicProperties.getChat(), message).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
