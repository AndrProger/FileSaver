package kz.test.filesaver.configs;

import kz.test.filesaver.handlers.WebSocketHandler;
import kz.test.filesaver.managers.WebSocketSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

/**
 * WebSocketConfig is a configuration class that sets up WebSocket communication for the
 * application. It implements the WebSocketConfigurer interface which provides callback methods to
 * customize the WebSocket configuration provided by Spring Boot.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  private static final String WS_ENDPOINT = "/ws";

  // sessionManager is an instance of WebSocketSessionManager which manages WebSocket sessions.
  private final WebSocketSessionManager sessionManager;

  /**
   * Constructor for WebSocketConfig.
   *
   * @param sessionManager An instance of WebSocketSessionManager.
   */
  public WebSocketConfig(WebSocketSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * This method is used to register WebSocket handlers.
   *
   * @param webSocketHandlerRegistry An instance of WebSocketHandlerRegistry.
   */
  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    webSocketHandlerRegistry.addHandler(getWebSocketHandler(), WS_ENDPOINT).setAllowedOrigins("*");
  }

  /**
   * This method is used to create a new instance of WebSocketHandler.
   *
   * @return A new instance of WebSocketHandler.
   */
  @Bean
  public WebSocketHandler getWebSocketHandler() {
    return new WebSocketHandler(sessionManager);
  }
}
