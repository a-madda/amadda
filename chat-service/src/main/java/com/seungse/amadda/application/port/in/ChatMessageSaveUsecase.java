package com.seungse.amadda.application.port.in;

public interface ChatMessageSaveUsecase {

    void saveChatMessage(String roomId, Long senderId, String message, String sentAt);

}
