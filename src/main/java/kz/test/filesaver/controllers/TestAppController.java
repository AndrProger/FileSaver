package kz.test.filesaver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestAppController {
    @GetMapping("/test")
    public String chat() {
        return "test";
    }
}
