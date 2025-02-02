package com.example.demo.config;


import com.example.demo.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    //역할(Role)	사용자명 (Username)	비밀번호 (Password)
    //관리자 (ADMIN)	admin	admin123
    //일반 사용자 (USER)	user	user123
    ////////////////////////////////////////////////////////////////////////////////////////////
    /// 1.  security에서 제공하는 기본 form사용!
    ///     데이터는 처음에 load
    ///     부트 프로젝트 시작하면, 로그인 화면이 보이고, 로그인 성공하면 /로 시작함.
    ///     layout.html에 인증여부에 따라 login/logout보이게 수정해야함.
    /*           
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic();

        return http.build();
    }
     */
    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////
    ///  2. custome한 login form있는 html파일 지정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**", "/chat/chat").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login // 기본 로그인 폼 활성화
                        .loginPage("/login") // 커스텀 로그인 페이지 지정 가능
                        .defaultSuccessUrl("/user-info", true)
                        .permitAll()
                )
                .logout(logout ->
                        logout.logoutSuccessUrl("/"))
                .sessionManagement(session ->
                        //session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
                // ❌ 세션을 사용하지 않는 STATELESS 설정이므로, JWT 없이 인증 유지가 불가능함
                // 👉 JWT 방식이 아니라면 SessionCreationPolicy.STATELESS를 IF_REQUIRED로 변경하는 것이 필요

        return http.build();
    }


    /*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
