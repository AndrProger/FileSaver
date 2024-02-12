package kz.test.filesaver.handlers;

import static kz.test.filesaver.utils.LoggingUtility.info;

import kz.test.filesaver.managers.WebSocketSessionManager;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * This class extends the TextWebSocketHandler to handle WebSocket connections. It manages WebSocket
 * sessions and handles text messages received from the client.
 */
public class WebSocketHandler extends TextWebSocketHandler {

  /** The manager for WebSocket sessions. */
  private final WebSocketSessionManager sessionManager;

  /**
   * Constructor for WebSocketHandler.
   *
   * @param sessionManager The manager for WebSocket sessions.
   */
  public WebSocketHandler(WebSocketSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * This method is called after a WebSocket connection is established. It logs the connection and
   * adds the session to the session manager.
   *
   * @param session The WebSocket session.
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    info("Connection established: " + session.getId());
    sessionManager.addSession(session);
  }

  /**
   * This method handles text messages received from the client. It logs the message and delegates
   * the handling to the session manager.
   *
   * @param session The WebSocket session.
   * @param message The text message received from the client.
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    info("Receive message: " + message.getPayload() + " from " + session.getId());
    sessionManager.handleMessage(message.getPayload(), session);
  }

  /**
   * This method is called after a WebSocket connection is closed. It logs the closure and removes
   * the session from the session manager.
   *
   * @param session The WebSocket session.
   * @param status The status of the connection closure.
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {
    info("Connection closed: " + session.getId());
    sessionManager.removeSession(session);
  }
}
