package com.Kim.security.config;

//import com.Kim.security.config.oauth.PrincipalOauth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //리턴되는 객체를 IoC로 등록
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        return http
                .csrf(CsrfConfigurer::disable)
                .requestCache(request -> request
                        .requestCache(requestCache))
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers(new AntPathRequestMatcher("/user/**")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("ADMIN", "MANAGER")
                                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                )
                .getOrBuild();
    }
}