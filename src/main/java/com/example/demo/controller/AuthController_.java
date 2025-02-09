package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@Controller
//@RequestMapping("/api")
//@RequiredArgsConstructor
public class AuthController_ {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    private final UserDetailsServiceImpl userDetailsService;
///*
//    @PostMapping("/login")
//    public Map<String, String> login(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        String token = jwtUtil.generateToken(userDetails.getUsername());
//        System.out.println("#####################################\n");
//        System.out.println("###### 발급받은 token: " + token);
//        System.out.println("#####################################\n");
//        return Map.of("token", token);
//    }
//*/
//
//    @PostMapping("/login3")
//    @ResponseBody
//    public String login(@RequestBody User user, HttpServletResponse response) {
//
//        System.out.println("############## 서버로 전달################");
//        System.out.println("===================>>>> " + user);
//        String username = user.getUsername();
//        String password = user.getPassword();
//        Authentication authentication = null;
//
//        String result = "로그인 성공!!";
//        // 인증 시도
//        try {
//            authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password));
//        } catch (BadCredentialsException e) {
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ >> " + authentication);
//            //System.out.println("$$$$$$ >>>>> "  + authentication.getPrincipal());
//            result = "로그인 실패: 잘못된 사용자 이름 또는 비밀번호";
//        }
//        System.out.println("==================>>>> 로그인 성공");;
//        // 사용자 정보 조회
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        System.out.println("==================>>>> " + userDetails);
//        if (userDetails == null) {
//            result =  "로그인 실패: 사용자 정보를 찾을 수 없습니다";  // DB에서 사용자 찾을 수 없으면 로그인 실패 처리
//        }
//
//        // 첫 번째 권한을 바로 꺼내기
//        String role = userDetails.getAuthorities().isEmpty() ?
//                "ROLE_USER" : userDetails.getAuthorities().iterator().next().getAuthority();
//
//        // JWT 토큰 생성
//        String token = jwtUtil.generateToken(userDetails.getUsername(), role);
//
//        // HTTPOnly 쿠키 설정
//        Cookie cookie = new Cookie("JWT_TOKEN", token);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(86400); // 1일 (초 단위)
//        response.addCookie(cookie);
//
//        return result;
//    }
//
//    @GetMapping("/logout3")
////    @ResponseBody
//    public String logout(HttpServletResponse response) {
//        Cookie cookie = new Cookie("JWT_TOKEN", "");
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(0); // 즉시 만료
//        response.addCookie(cookie);
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/csrf-token")
//    @ResponseBody
//    public Map<String, String> getCsrfToken(HttpServletRequest request) {
//        // Spring Security에서 자동으로 생성한 CSRF 토큰 가져오기
//        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
//        System.out.println("1 ---- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        System.out.println(csrfToken.getToken());
//        System.out.println("1 ---- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        if (csrfToken != null) {
//            return Map.of("token", csrfToken.getToken());
//        } else {
//            return Map.of("error", "CSRF token not found");
//        }
//    }
//
//    @GetMapping("/user-info3")
//    @ResponseBody
//    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
//        String userId = userDetails != null ? userDetails.getUsername() : "guest";
//        System.out.println("2 ---- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        if (userDetails != null) {
//            userDetails.getAuthorities().parallelStream().forEach(System.out::println);
//        }
//        System.out.println("2 ---- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        System.out.println("로그인한 userId = " + userId);
//        return userId;
//    }
}
