package cn.pri.smilly.oauthserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/home")
    public String hello() {
        return "hello welcome to the world of spring oauth2 !";
    }

    @GetMapping("/web")
    public String web() {
        return "congratulations ! you have been successfully learned authorize_code mode !";
    }

    @GetMapping("/app")
    public String android() {
        return "congratulations ! you have been successfully learned password mode!";
    }

}
