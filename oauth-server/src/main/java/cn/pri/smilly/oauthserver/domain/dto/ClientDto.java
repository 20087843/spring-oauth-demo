package cn.pri.smilly.oauthserver.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class ClientDto implements Serializable {
    private String clientId;
    private String secret;
    private String scope;
    private String resourceId;
    private String[] authorities;
    private String[] authorizedGrantTypes;
    private String registeredRedirectUri;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
}
