package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * ✅ 로그인 API (JWT 발급)
     */
    @PostMapping("/login3")
    public Map<String, String> login(@RequestBody User user, HttpServletResponse response) {
        System.out.println("📢 로그인 요청: " + user);

        String username = user.getUsername();
        String password = user.getPassword();
        Authentication authentication;

        // 인증 시도
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            return Map.of("error", "로그인 실패: 잘못된 사용자 이름 또는 비밀번호");
        }

        // 사용자 정보 조회
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            return Map.of("error", "로그인 실패: 사용자 정보를 찾을 수 없습니다");
        }

        System.out.println(userDetails.getUsername() + "==============\n" +
                userDetails.getPassword() + "==============\n" +
                userDetails.getAuthorities());

        // 역할(Role) 가져오기
        String role = userDetails.getAuthorities().isEmpty() ? "ROLE_USER"
                : userDetails.getAuthorities().iterator().next().getAuthority();

        // ✅ JWT 토큰 생성
        String token = jwtUtil.generateToken(userDetails.getUsername(), role);

        // ✅ HTTPOnly, Secure 쿠키에 JWT 저장
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS 환경에서만 전송
        cookie.setPath("/");
        cookie.setMaxAge(86400); // 1일 (초 단위)
        response.addCookie(cookie);

        System.out.println("✅ 발급된 JWT: " + token);
        return Map.of("message", "성공", "token", token);
    }

    /**
     * ✅ 로그아웃 API (JWT 삭제)
     */
    @PostMapping("/logout3")
    public Map<String, String> logout(HttpServletResponse response) {
        // ✅ 쿠키에서 JWT 제거
        Cookie cookie = new Cookie("accessToken", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 즉시 만료
        response.addCookie(cookie);

        return Map.of("message", "로그아웃 성공!");
    }

    /**
     * ✅ 로그인한 사용자 정보 조회 API (SecurityContext에서 가져오기)
     */
    @GetMapping("/user-info3")
    @ResponseBody
    public Map<String, String> getUserInfo(Authentication authentication) {

        // ✅ SecurityContextHolder에서 사용자 정보 가져오기
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String role = authentication.getAuthorities().iterator().next().getAuthority();

            System.out.println("✅ SecurityContext에서 가져온 사용자: " + username + " | 역할: " + role);
            return Map.of("username", username, "role", role);
        }

        // ✅ 인증되지 않은 경우
        return Map.of("error", "로그인 정보 없음");
    }
}
