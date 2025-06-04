package com.seungse.amadda.infrastructure.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungse.amadda.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String channel = new String(message.getChannel());
            String body = new String(message.getBody());
            log.info("Received message from channel: {}, body: {}", channel, body);

            String publishMessage = (String)redisTemplate.getStringSerializer().deserialize(message.getBody());
            // Deserialize the message body to a ChatMessage object
            ChatMessage chatMessage = objectMapper.readValue(publishMessage, ChatMessage.class);

            // Send the message to the WebSocket topic
            simpMessagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
        }
    }

}
