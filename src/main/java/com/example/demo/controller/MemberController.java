package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Value
@RequestMapping("member")
@RequiredArgsConstructor
@Controller
public class MemberController {

    MemberService memberService;

    @GetMapping("member")
    public String member() {
        return "member/member";
    }

    @GetMapping("read")
    @ResponseBody
    public String read(@RequestParam("id") String id) {
        return id + "검색 결과";
    }
    @PostMapping("login")
    public String login(HttpSession session, String memberId) {
        Member member = memberService.findMemberById(memberId);
        if (member != null) {
            session.setAttribute("loginId", member.getMemberId());
        }
        return "chat2";
    }
}
