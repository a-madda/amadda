package com.seungse.amadda.application.service;

import com.seungse.amadda.application.port.out.ChatRoomSearchOutPort;
import com.seungse.amadda.application.port.in.ChatMessageSaveUsecase;
import com.seungse.amadda.application.port.in.ChatRoomSaveUsecase;
import com.seungse.amadda.application.port.out.ChatRoomSaveOutPort;
import com.seungse.amadda.domain.ChatRoom;
import com.seungse.amadda.domain.ChatType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomSaveService implements ChatRoomSaveUsecase, ChatMessageSaveUsecase {

    private final ChatRoomSaveOutPort chatRoomSaveOutPort;
    private final ChatRoomSearchOutPort chatRoomSearchOutPort;

    @Override
    public ChatRoom saveChatRoom(String roomId, String name, ChatType chatType, Long ownerId) {
        return chatRoomSaveOutPort.saveChatRoom(ChatRoom.builder().roomId(roomId).chatType(chatType).name(name).build(), ownerId);
    }

    @Override
    public void saveChatMessage(String roomId, Long senderId, String message, String sentAt) {
        ChatRoom chatRoom = ChatRoom.builder().roomId(roomId).build();
        if(!chatRoomSearchOutPort.existsChatRoom(UUID.fromString(roomId))) {
            throw new IllegalArgumentException("Chat room not found");
        }
        chatRoom.addChat(roomId, senderId, message, sentAt);
        chatRoomSaveOutPort.sendMessage(chatRoom);
    }
}
