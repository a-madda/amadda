package com.seungse.amadda.application.port.out;

import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.domain.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatOutPort {

    ChatRoom createGroupChatRoom(String name);

    void enterChatRoom(String roomId);

    void sendMessage(ChatMessage chatMessage);

    List<ChatRoom> getAllChatRooms();

    Optional<ChatRoom> getChatRoom(String roomId);

}
