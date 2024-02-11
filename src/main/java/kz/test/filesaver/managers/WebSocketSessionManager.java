package kz.test.filesaver.managers;

import kz.test.filesaver.facade.LogEntryFacade;
import kz.test.filesaver.utils.LoggingUtility;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketSessionManager extends TextWebSocketHandler {

    private final LogEntryFacade logEntryFacade;
    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();

    public WebSocketSessionManager(LogEntryFacade logEntryFacade) {
        this.logEntryFacade = logEntryFacade;
    }

    public void addSession(WebSocketSession session) {
        webSocketSessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        webSocketSessions.remove(session);
    }

    public void handleMessage(String message) {
        if("PING".equals(message.trim())) {
            broadcastToAll(getPongMessage());
        }
    }

    private void sendMessageToSession(WebSocketSession session, String message) {
        if(session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                LoggingUtility.error("Error while sending message to session: " + session.getId());
            }
        }
    }

    public void broadcastToSession(WebSocketSession targetSession, String message) {
        sendMessageToSession(targetSession, message);
    }

    public void broadcastToAll(String message) {
        for (WebSocketSession session : webSocketSessions) {
            sendMessageToSession(session, message);
        }
    }

    private String getPongMessage() {
        return "PONG\nLog size: " + logEntryFacade.getTotalLogEntries();
    }
}
