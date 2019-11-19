package cn.pri.smilly.oauthserver.controller;

import cn.pri.smilly.oauthserver.domain.dto.ClientDto;
import cn.pri.smilly.oauthserver.service.MemoryClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private MemoryClientDetailsService clientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void addClient(ClientDto client) {
        clientDetailsService.withClient(client.getClientId())
                .secret(passwordEncoder.encode(client.getSecret()))
                .scopes(client.getScope())
                .resourceIds(client.getResourceId())
                .authorities(client.getAuthorities())
                .authorizedGrantTypes(client.getAuthorizedGrantTypes())
                .redirectUris(client.getRegisteredRedirectUri())
                .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                .build();
    }

    @DeleteMapping
    public void deleteClient(String clientId) {
        clientDetailsService.deleteClient(clientId);
    }

    @GetMapping
    public List<ClientDetails> getClients() {
        return clientDetailsService.getClients();
    }
}
