package com.seungse.amadda.application.port.out;

import com.seungse.amadda.adapter.out.persistance.entity.ChatType;
import com.seungse.amadda.domain.ChatRoom;

public interface ChatRoomSaveOutPort {

    ChatRoom saveChatRoom(ChatRoom chatRoom, ChatType chatType, Long ownerId);

}
