package com.seungse.amadda.application.port.out;

import java.util.UUID;

public interface ChatRoomSearchOutPort {
    boolean existsChatRoom(UUID roomId);
}
