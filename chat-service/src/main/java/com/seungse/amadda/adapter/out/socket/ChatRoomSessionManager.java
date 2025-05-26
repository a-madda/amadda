package com.seungse.amadda.adapter.out.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatRoomSessionManager {
    private final Map<String, Set<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    public void addSession(String roomId, WebSocketSession session) {
        roomSessions.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(session);
    }

    public void removeSession(String roomId, WebSocketSession session) {
        Set<WebSocketSession> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
        }
    }

    public Set<WebSocketSession> getSessions(String roomId) {
        return roomSessions.getOrDefault(roomId, Collections.emptySet());
    }

    public void clearRoom(String roomId) {
        roomSessions.remove(roomId);
    }

}