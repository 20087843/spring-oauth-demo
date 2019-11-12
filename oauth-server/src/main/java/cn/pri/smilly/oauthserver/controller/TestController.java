package cn.pri.smilly.oauthserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/user")
    public Principal current(Principal principal) {
        return principal;
    }

    @GetMapping("/web")
    public String web() {
        return "hello web!";
    }

    @GetMapping("/app")
    public String android() {
        return "hello app!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }
}
