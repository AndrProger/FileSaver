package kz.test.filesaver.handlers;

import static kz.test.filesaver.utils.LoggingUtility.info;

import kz.test.filesaver.managers.WebSocketSessionManager;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

  private final WebSocketSessionManager sessionManager;

  public WebSocketHandler(WebSocketSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    info("Connection established: " + session.getId());
    sessionManager.addSession(session);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    info("Receive message: " + message.getPayload() + " from " + session.getId());
    sessionManager.handleMessage(message.getPayload(), session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {
    info("Connection closed: " + session.getId());
    sessionManager.removeSession(session);
  }
}
