package com.example.demo.service;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs2;
import com.example.demo.repository.Bbs2Repository;
import com.example.demo.repository.BbsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Bbs2Service {

    private final Bbs2Repository bbs2Repository;

    public Page<Bbs2> findAll(Pageable pageable) {
        return bbs2Repository.findAll(pageable);
    }
}