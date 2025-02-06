package com.example.demo;

import com.example.demo.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired //의존성 주입
    private MemberService memberService;

    @Test
    void deleteTest() {
        int result = memberService.deleteMember(100);
        Assertions.assertEquals(1, result, "1이 나와야 합니다.");
    }
}
