package cn.pri.smilly.oauthserver.config;

import cn.pri.smilly.oauthserver.service.MemoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MemoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        userDetailsService.saveUser(User.builder().username("user_1").password(passwordEncoder().encode("123456")).roles("USER").build());
        userDetailsService.saveUser(User.builder().username("user_2").password(passwordEncoder().encode("123456")).roles("USER").build());
        userDetailsService.saveUser(User.builder().username("admin").password(passwordEncoder().encode("123456")).roles("ADMIN").build());
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()  // 拦截所有的路径
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/client").hasRole("USER")
                .antMatchers("/user").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().logoutSuccessUrl("/login")
                .and().rememberMe()
                .and().httpBasic();
    }
}
