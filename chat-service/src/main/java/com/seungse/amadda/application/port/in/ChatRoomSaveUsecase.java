package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.ChatRoom;
import com.seungse.amadda.domain.ChatType;

public interface ChatRoomSaveUsecase {

    ChatRoom saveChatRoom(String roomId, String name, ChatType chatType, Long ownerId);

}
