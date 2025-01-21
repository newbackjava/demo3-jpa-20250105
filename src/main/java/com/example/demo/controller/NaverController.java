package com.example.demo.controller;

import com.example.demo.dto.NaverDto;
import com.example.demo.entity.Naver;
import com.example.demo.service.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Value
@RequestMapping("naver")
public class NaverController {

    private NaverService naverService;

    @GetMapping("login")
    public String login() {
        return "naver/login";
    }

    @GetMapping("singUp")
    public String singUp(NaverDto naverDto, Model model) {
        Naver naver = Naver.builder()
                        .email(naverDto.getEmail())
                        .password(naverDto.getPassword())
                        .nickname(naverDto.getNickname())
                        .gender(naverDto.getGender())
                        .build();
        System.out.println("naverDto = " + naverDto);
        System.out.println("naver = " + naver);

        naverService.insert(naver);
        model.addAttribute("naverDto", naverDto);
        return "naver/singUp";
    }
}
