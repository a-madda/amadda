package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.ChatRoom;

public interface ChatRoomSaveUsecase {

    ChatRoom saveChatRoom(String roomId, String name, Long ownerId);

}
