package cn.pri.smilly.oauthserver.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryUserDetailsService implements UserDetailsService {
    private List<UserDetails> userCache = new ArrayList<>();

    public void saveUser(UserDetails user) {
        userCache.add(user);
    }

    public void deleteUser(String username) {
        userCache.stream().dropWhile(user -> user.getUsername().equals(username));
    }

    public List<UserDetails> getUsers(){
        return userCache;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCache.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElseGet(null);
    }
}
