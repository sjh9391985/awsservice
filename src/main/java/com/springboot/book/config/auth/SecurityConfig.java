package com.springboot.book.config.auth;

import com.springboot.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable() // h2-console 화면을 사용하기위해 옵션 disable
            .and()
            .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되야 antMatchers 옵션사용가능
            .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.USER.name())
            .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL들을 나타냄
            .and()
            .logout()
            .logoutSuccessUrl("/") // logout 성공시 '/' 주소로 이동
            .and()
            .oauth2Login() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
            .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정들을 담당함
            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 인터페이스의 구현체를 등록
    }

}
