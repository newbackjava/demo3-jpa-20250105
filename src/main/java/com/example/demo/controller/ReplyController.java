package com.example.demo.controller;

import com.example.demo.dto.AllDto;
import com.example.demo.entity.Bbs;
import com.example.demo.service.BbsService;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/all")
    public ResponseEntity<List<AllDto>> getBbsDetails(String writer, Model model) {
        List<AllDto> list = replyService.getAll(writer);
        model.addAttribute("all", list);
        return ResponseEntity.ok(list);
    }
}