package com.seungse.amadda.adapter.out.persistance;

import com.seungse.amadda.domain.ChatRoom;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap = new LinkedHashMap<>();

    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public Optional<ChatRoom> findChatRoom(int roomId) {
        return Optional.ofNullable(chatRoomMap.get(String.valueOf(roomId)));
    }

    public ChatRoom save(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
