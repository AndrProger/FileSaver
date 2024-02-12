package kz.test.filesaver.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kz.test.filesaver.facade.LogEntryFacade;
import kz.test.filesaver.utils.LoggingUtility;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/** This class manages WebSocket sessions and handles messages. */
@Component
public class WebSocketSessionManager extends TextWebSocketHandler {

  /** Facade for log entries. */
  private final LogEntryFacade logEntryFacade;

  /** List of active WebSocket sessions. */
  private final List<WebSocketSession> webSocketSessions = new ArrayList<>();

  /**
   * Constructor for WebSocketSessionManager.
   *
   * @param logEntryFacade Facade for log entries.
   */
  public WebSocketSessionManager(LogEntryFacade logEntryFacade) {
    this.logEntryFacade = logEntryFacade;
  }

  /**
   * Adds a new WebSocket session to the list of active sessions.
   *
   * @param session The WebSocket session to be added.
   */
  public void addSession(WebSocketSession session) {
    webSocketSessions.add(session);
  }

  /**
   * Removes a WebSocket session from the list of active sessions.
   *
   * @param session The WebSocket session to be removed.
   */
  public void removeSession(WebSocketSession session) {
    webSocketSessions.remove(session);
  }

  /**
   * Handles incoming messages from a WebSocket session.
   *
   * @param message The incoming message.
   * @param session The WebSocket session that sent the message.
   */
  public void handleMessage(String message, WebSocketSession session) {
    if ("PING".equals(message.trim())) {
      broadcastToSession(session, getPongMessage());
    }
  }

  /**
   * Sends a message to a specific WebSocket session.
   *
   * @param session The WebSocket session to send the message to.
   * @param message The message to be sent.
   */
  private void sendMessageToSession(WebSocketSession session, String message) {
    if (session.isOpen()) {
      try {
        session.sendMessage(new TextMessage(message));
      } catch (IOException e) {
        LoggingUtility.error("Error while sending message to session: " + session.getId());
      }
    }
  }

  /**
   * Sends a message to a specific WebSocket session.
   *
   * @param targetSession The WebSocket session to send the message to.
   * @param message The message to be sent.
   */
  public void broadcastToSession(WebSocketSession targetSession, String message) {
    sendMessageToSession(targetSession, message);
  }

  /**
   * Sends a message to all active WebSocket sessions.
   *
   * @param message The message to be sent.
   */
  public void broadcastToAll(String message) {
    for (WebSocketSession session : webSocketSessions) {
      sendMessageToSession(session, message);
    }
  }

  /**
   * Returns a pong message with the total number of log entries.
   *
   * @return The pong message.
   */
  public String getPongMessage() {
    return "PONG\nLog size: " + logEntryFacade.getTotalLogEntries();
  }
}
