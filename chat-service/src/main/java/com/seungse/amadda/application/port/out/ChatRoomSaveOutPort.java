package com.seungse.amadda.application.port.out;

import com.seungse.amadda.domain.ChatRoom;

public interface ChatRoomSaveOutPort {

    ChatRoom saveChatRoom(ChatRoom chatRoom, Long ownerId);

    void sendMessage(ChatRoom chatRoom);

}
