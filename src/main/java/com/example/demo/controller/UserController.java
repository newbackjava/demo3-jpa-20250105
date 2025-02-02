package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    public String userEndpoint() {
        return "Welcome, User!";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String adminEndpoint() {
        return "Welcome, Admin!";
    }

    //login.html 에서의 post요청은 spring security에서 자동 처리함.
    @GetMapping("/login")
    public String login() {
        return "user/login"; // login.html 템플릿을 반환
    }

    //login.html 에서의 post요청은 spring security에서 자동 처리함.
    @GetMapping("/login2")
    public String login2() {
        return "user/login2"; // login.html 템플릿을 반환
    }

    @GetMapping("/user-info")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails != null ? userDetails.getUsername() : "guest";
        System.out.println("로그인한 userId = " + userId);
        return "user/userInfo";
    }
    /*
    @GetMapping("/user-info")
    public String getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return "현재 로그인한 사용자: " + userDetails.getUsername();
        }
        return "로그인하지 않은 사용자입니다.";
    }
     */
}
