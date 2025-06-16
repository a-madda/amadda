package com.seungse.amadda.infrastructure.publisher;

import com.seungse.amadda.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, ChatMessage message) {
        log.info("Publishing message to channel: {}, body: {}", topic, message);
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}
