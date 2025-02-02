package com.example.demo.service;

import com.example.demo.dto.AllDto;
import com.example.demo.entity.Reply;
import com.example.demo.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public List<AllDto> getAll(String writer) {
        return replyRepository.findByWriter(writer);
    }

    public Reply save(Reply reply) {
        return replyRepository.save(reply);
    }
}
