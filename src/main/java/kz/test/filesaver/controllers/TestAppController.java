package kz.test.filesaver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** This class is a Spring controller for handling requests to the "/test" endpoint. */
@Controller
public class TestAppController {

  /**
   * Handles GET requests to the "/test" endpoint.
   *
   * @return app HTML page for test functionality.
   */
  @GetMapping("/app")
  public String app() {
    return "app";
  }
}
