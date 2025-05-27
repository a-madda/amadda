package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.domain.ChatRoom;
import com.seungse.amadda.infrastructor.subscriber.RedisSubscriber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber redisSubscriber;
    private static final String CHAT_ROOM_CHANNEL = "chatRoomChannel";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> hashOperations;
    private Map<String, ChannelTopic> topics = new LinkedHashMap<>();

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
        if(topic != null) {
            topic = new ChannelTopic(roomId);
            redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.computeIfAbsent(roomId, ChannelTopic::new);
    }

    public List<ChatRoom> findAllRoom() {
        return hashOperations.values(CHAT_ROOM_CHANNEL);
    }

    public Optional<ChatRoom> findChatRoom(String roomId) {
        return Optional.ofNullable(hashOperations.get(CHAT_ROOM_CHANNEL, roomId));
    }

    public ChatRoom save(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        hashOperations.put(CHAT_ROOM_CHANNEL, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
