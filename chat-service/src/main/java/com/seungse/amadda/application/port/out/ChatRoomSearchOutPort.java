package com.seungse.amadda.application.port.out;

import com.seungse.amadda.domain.ChatRoom;

import java.util.Optional;
import java.util.UUID;

public interface ChatRoomSearchOutPort {
    boolean existsChatRoom(UUID roomId);

    Optional<ChatRoom> findChatRoomById(UUID roomId);

}
