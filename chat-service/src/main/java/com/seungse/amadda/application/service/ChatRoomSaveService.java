package com.seungse.amadda.application.service;

import com.seungse.amadda.application.port.in.ChatRoomSaveUsecase;
import com.seungse.amadda.application.port.out.ChatRoomSaveOutPort;
import com.seungse.amadda.domain.ChatRoom;
import com.seungse.amadda.domain.ChatType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomSaveService implements ChatRoomSaveUsecase {

    private final ChatRoomSaveOutPort chatRoomSaveOutPort;

    @Override
    public ChatRoom saveChatRoom(String roomId, String name, ChatType chatType, Long ownerId) {
        return chatRoomSaveOutPort.saveChatRoom(ChatRoom.builder().roomId(roomId).chatType(chatType).name(name).build(), ownerId);
    }

}
