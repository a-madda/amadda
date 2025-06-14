package com.seungse.amadda.adapter.out.persistance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seungse.amadda.adapter.out.produce.MessageProducer;
import com.seungse.amadda.application.port.out.ChatOutPort;
import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.domain.ChatRoom;
import com.seungse.amadda.domain.MessageType;
import com.seungse.amadda.infrastructure.publisher.RedisPublisher;
import com.seungse.amadda.infrastructure.subscriber.RedisSubscriber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ChatOutPortAdapter implements ChatOutPort {


    private final RedisPublisher redisPublisher;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber redisSubscriber;
    private static final String CHAT_ROOM_CHANNEL = "chatRoomChannel";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> hashOperations;
    private final Map<String, ChannelTopic> topics = new LinkedHashMap<>();
    private final MessageProducer messageProducer;

    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        hashOperations.put(CHAT_ROOM_CHANNEL, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public void enterChatRoom(String roomId) {
        ChannelTopic topic = this.topics.get(roomId);
        this.topics.computeIfPresent( roomId, (key, value) -> {
            value = new ChannelTopic(roomId);
            redisMessageListenerContainer.addMessageListener(redisSubscriber, value);
            topics.put(roomId, topic);
            return value;
        });
    }

    @Override
    public void sendMessage(ChatMessage chatMessage) {
        if(MessageType.ENTER.equals(chatMessage.getType())) {
            enterChatRoom(chatMessage.getRoomId());
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        }
        chatMessage.setSentTime();
        log.info("sending message: {}", chatMessage);
        redisPublisher.publish(this.getTopic(chatMessage.getRoomId()), chatMessage);
        /*try {
            messageProducer.produce(chatMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to produce message", e);
        }*/
    }

    private ChannelTopic getTopic(String roomId) {
        return topics.computeIfAbsent(roomId, ChannelTopic::new);
    }

    public List<ChatRoom> getAllChatRooms() {
        return hashOperations.values(CHAT_ROOM_CHANNEL);
    }

    public Optional<ChatRoom> getChatRoom(String roomId) {
        return Optional.ofNullable(hashOperations.get(CHAT_ROOM_CHANNEL, roomId));
    }

}
