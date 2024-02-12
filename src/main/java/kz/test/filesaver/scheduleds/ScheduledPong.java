package kz.test.filesaver.scheduleds;

import kz.test.filesaver.managers.WebSocketSessionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledPong {

  private final WebSocketSessionManager sessionManager;

  public ScheduledPong(WebSocketSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Scheduled(fixedRate = 10000)
  public void executeTask() {
    sessionManager.broadcastToAll(sessionManager.getPongMessage());
  }
}
