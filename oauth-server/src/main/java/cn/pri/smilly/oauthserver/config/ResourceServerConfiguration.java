package cn.pri.smilly.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * ResourceServerConfigurerAdapter 的优先级为3, 比 WebSecurityConfigurerAdapter 100 要高,
 * ResourceServerConfigurerAdapter 中的拦截配置规则先起作用, 因此配置
 * ResourceServerConfigurerAdapter 不能将 ResourceServerConfigurerAdapter 中的拦截配置覆盖
 * 本项目中，, ResourceServer 只拦截以 /api 开头的路径, 即项目资源路径
 * http.antMather("/api/**") 即定义 httpSecurityConfig 拦截根目录
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**") // 只拦截以 /api 开头的路径
                .authorizeRequests()
                .antMatchers("/client").hasRole("USER")
                .antMatchers("/user").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }
}
