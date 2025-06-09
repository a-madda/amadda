package com.seungse.amadda.application.service;

import com.seungse.amadda.application.port.in.ChatUseCase;
import com.seungse.amadda.application.port.out.ChatOutPort;
import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService implements ChatUseCase {

    private final ChatOutPort chatOutPort;

    @Override
    public ChatRoom createChatRoom(String name) {
        return chatOutPort.createChatRoom(name);
    }

    @Override
    public void joinChatRoom(String roomId, String userId) {
        chatOutPort.enterChatRoom(roomId);
    }

    @Override
    public void sendMessage(ChatMessage chatMessage) {
        chatOutPort.sendMessage(chatMessage);
    }

    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatOutPort.getAllChatRooms();
    }

    @Override
    public Optional<ChatRoom> getChatRoom(String roomId) {
        return chatOutPort.getChatRoom(roomId);
    }

}
