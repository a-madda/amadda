package com.seungse.amadda.application.service;

import com.seungse.amadda.adapter.out.persistance.entity.ChatType;
import com.seungse.amadda.application.port.in.ChatRoomSaveUsecase;
import com.seungse.amadda.application.port.out.ChatRoomSaveOutPort;
import com.seungse.amadda.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomSaveService implements ChatRoomSaveUsecase {

    private final ChatRoomSaveOutPort chatRoomSaveOutPort;

    @Override
    public ChatRoom saveChatRoom(String roomId, String name, Long ownerId) {
        return chatRoomSaveOutPort.saveChatRoom(ChatRoom.builder().roomId(roomId).name(name).build(), ChatType.GROUP, ownerId);
    }

}
