package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

@Value
@Service
@RequiredArgsConstructor
public class MemberService {

    MemberRepository memberRepository;

    public Member findMemberById(String id) {
        return memberRepository.findById(id).orElse(null);
    }

    public int deleteMember(int id) {
        return 1;
    }


}
