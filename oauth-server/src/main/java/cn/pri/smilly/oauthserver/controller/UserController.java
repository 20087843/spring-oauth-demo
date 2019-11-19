package cn.pri.smilly.oauthserver.controller;

import cn.pri.smilly.oauthserver.domain.dto.UserDto;
import cn.pri.smilly.oauthserver.service.MemoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private MemoryUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void saveUser(UserDto user) {
        userDetailsService.saveUser(User.withUsername(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .authorities(user.getAuthorities())
                .build());
    }

    @GetMapping
    public List<UserDetails> getUsers() {
        return userDetailsService.getUsers();
    }

    @DeleteMapping
    public void deleteUser(String username) {
        userDetailsService.deleteUser(username);
    }

    @GetMapping("/current")
    public Object currentUser(Principal principal) {
        return principal;
    }

}
