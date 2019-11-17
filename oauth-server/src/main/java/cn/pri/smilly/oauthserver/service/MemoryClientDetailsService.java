package cn.pri.smilly.oauthserver.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service
public class MemoryClientDetailsService implements ClientDetailsService {
    public List<ClientDetails> clientCache = new ArrayList<>();

    public void saveClient(ClientDetails client) {
        clientCache.add(client);
    }

    @DeleteMapping
    public void deleteClient(String clientId) {
        clientCache.stream().dropWhile(client -> client.getClientId().equals(clientId));
    }

    @GetMapping
    public List<ClientDetails> getClients() {
        return clientCache;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientCache.stream().filter(client -> client.getClientId().equals(clientId)).findFirst().orElseGet(null);
    }

    public ClientBuilder withClient(String clientId) {
        return new ClientBuilder(clientId);
    }

    public final class ClientBuilder {
        private String clientId;
        private String secret;
        private Collection<String> authorizedGrantTypes;
        private Collection<String> authorities;
        private Integer accessTokenValiditySeconds;
        private Integer refreshTokenValiditySeconds;
        private Collection<String> scopes;
        private Collection<String> autoApproveScopes;
        private Set<String> registeredRedirectUris;
        private Set<String> resourceIds;
        private boolean autoApprove;
        private Map<String, Object> additionalInformation;

        public MemoryClientDetailsService build() {
            BaseClientDetails result = new BaseClientDetails();
            result.setClientId(this.clientId);
            result.setAuthorizedGrantTypes(this.authorizedGrantTypes);
            result.setAccessTokenValiditySeconds(this.accessTokenValiditySeconds);
            result.setRefreshTokenValiditySeconds(this.refreshTokenValiditySeconds);
            result.setRegisteredRedirectUri(this.registeredRedirectUris);
            result.setClientSecret(this.secret);
            result.setScope(this.scopes);
            result.setAuthorities(AuthorityUtils.createAuthorityList((String[]) this.authorities.toArray(new String[this.authorities.size()])));
            result.setResourceIds(this.resourceIds);
            result.setAdditionalInformation(this.additionalInformation);
            if (this.autoApprove) {
                result.setAutoApproveScopes(this.scopes);
            } else {
                result.setAutoApproveScopes(this.autoApproveScopes);
            }

            MemoryClientDetailsService.this.saveClient(result);
            return MemoryClientDetailsService.this;
        }

        public ClientBuilder resourceIds(String... resourceIds) {
            this.resourceIds.addAll(Arrays.asList(resourceIds));
            return this;
        }

        public ClientBuilder redirectUris(String... registeredRedirectUris) {
            this.registeredRedirectUris.addAll(Arrays.asList(registeredRedirectUris));
            return this;
        }

        public ClientBuilder authorizedGrantTypes(String... authorizedGrantTypes) {
            this.authorizedGrantTypes.addAll(Arrays.asList(authorizedGrantTypes));
            return this;
        }

        public ClientBuilder accessTokenValiditySeconds(int accessTokenValiditySeconds) {
            this.accessTokenValiditySeconds = accessTokenValiditySeconds;
            return this;
        }

        public ClientBuilder refreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
            this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
            return this;
        }

        public ClientBuilder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public ClientBuilder scopes(String... scopes) {
            this.scopes.addAll(Arrays.asList(scopes));
            return this;
        }

        public ClientBuilder authorities(String... authorities) {
            this.authorities.addAll(Arrays.asList(authorities));
            return this;
        }

        public ClientBuilder autoApprove(boolean autoApprove) {
            this.autoApprove = autoApprove;
            return this;
        }

        public ClientBuilder autoApprove(String... scopes) {
            this.autoApproveScopes.addAll(Arrays.asList(scopes));
            return this;
        }

        public ClientBuilder additionalInformation(Map<String, ?> map) {
            this.additionalInformation.putAll(map);
            return this;
        }

        public ClientBuilder additionalInformation(String... pairs) {
            for (int i = 0; i < pairs.length; ++i) {
                String pair = pairs[i];
                String separator = ":";
                if (!pair.contains(separator) && pair.contains("=")) {
                    separator = "=";
                }
                int index = pair.indexOf(separator);
                String key = pair.substring(0, index > 0 ? index : pair.length());
                String value = index > 0 ? pair.substring(index + 1) : null;
                this.additionalInformation.put(key, value);
            }
            return this;
        }

        public MemoryClientDetailsService and() {
            return MemoryClientDetailsService.this;
        }

        private ClientBuilder(String clientId) {
            this.clientId = clientId;
            this.authorizedGrantTypes = new LinkedHashSet();
            this.authorities = new LinkedHashSet();
            this.scopes = new LinkedHashSet();
            this.autoApproveScopes = new HashSet();
            this.registeredRedirectUris = new HashSet();
            this.resourceIds = new HashSet();
            this.additionalInformation = new LinkedHashMap();
        }
    }
}
