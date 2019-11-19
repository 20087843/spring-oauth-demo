package cn.pri.smilly.oauthresource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ClientController {

    @GetMapping("/normal")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String normal() {
        return "用户页面-1";
    }

    @GetMapping("/medium")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String medium() {
        return "用户页面-2";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "管理员页面";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
