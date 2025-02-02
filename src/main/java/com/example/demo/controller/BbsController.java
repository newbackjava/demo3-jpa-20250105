package com.example.demo.controller;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs;
import com.example.demo.entity.Bbs2;
import com.example.demo.service.Bbs2Service;
import com.example.demo.service.BbsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsController {

    private final Bbs2Service bbs2Service;

    //fetch join필요!
//    @GetMapping("/list/{memberId}")
//    public ResponseEntity<Page<MemberBbsDto>>
//        getBbsByTestUser(@PathVariable("memberId") String memberId,
//                         Pageable pageable) {
//        Page<MemberBbsDto> page = bbsService.getBbsByWriter(memberId, pageable);
//        return ResponseEntity.ok(page);
//    }

    //Bbs첫페이지
    @GetMapping("bbs")
    public String getBbs(Model model) {
        return "bbs/bbs";
    }

    //페이징 예제
    @GetMapping("list")
    public String getBbsList(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);  // 5개씩 페이지 나누기
        Page<Bbs2> list = bbs2Service.findAll(pageable);

        model.addAttribute("list", list);  // 페이지 정보
        return "bbs/list";  // HTML 페이지로 결과를 반환
    }
}