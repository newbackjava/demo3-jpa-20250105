package com.example.demo.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // ✅ SecurityContext에 인증 정보가 이미 있는 경우 JWT 검증 생략
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            // ✅ 쿠키에서 JWT 토큰 추출
            Optional<String> tokenOptional = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                    .filter(cookie -> "accessToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst();

            if (tokenOptional.isPresent()) {
                String token = tokenOptional.get();

                if (jwtUtil.validateToken(token)) {
                    String username = jwtUtil.getUsernameFromToken(token);
                    String role = jwtUtil.getRoleFromToken(token);

                    // ✅ Spring Security 권한 설정
                    List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

                    UserDetails userDetails = User.withUsername(username)
                            .password("[PROTECTED]") // ✅ 더미 값 (실제 비밀번호 저장 금지)
                            .authorities(authorities)
                            .build();

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
