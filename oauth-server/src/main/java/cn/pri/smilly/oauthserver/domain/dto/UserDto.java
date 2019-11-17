package cn.pri.smilly.oauthserver.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    private String password;
    private String username;
    private String[] authorities;
}
