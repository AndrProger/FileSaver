package kz.test.filesaver.scheduleds;

import kz.test.filesaver.managers.WebSocketSessionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for scheduling a task that sends a "pong" message to all active WebSocket sessions.
 */
@Component
public class ScheduledPong {

  /**
   * The manager of WebSocket sessions.
   */
  private final WebSocketSessionManager sessionManager;

  /**
   * Constructor for ScheduledPong.
   *
   * @param sessionManager The manager of WebSocket sessions.
   */
  public ScheduledPong(WebSocketSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * This method is scheduled to run at a fixed rate of 10 seconds.
   * It broadcasts a "pong" message to all active WebSocket sessions.
   */
  @Scheduled(fixedRate = 10000)
  public void executeTask() {
    sessionManager.broadcastToAll(sessionManager.getPongMessage());
  }
}
