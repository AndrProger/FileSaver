package kz.test.filesaver.configs;

import kz.test.filesaver.handlers.WebSocketHandler;
import kz.test.filesaver.managers.WebSocketSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  private static final String WS_ENDPOINT = "/ws";
  private final WebSocketSessionManager sessionManager;

  public WebSocketConfig(WebSocketSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    webSocketHandlerRegistry
        .addHandler(getChatWebSocketHandler(), WS_ENDPOINT)
        .setAllowedOrigins("*");
  }

  @Bean
  public WebSocketHandler getChatWebSocketHandler() {
    return new WebSocketHandler(sessionManager);
  }
}
