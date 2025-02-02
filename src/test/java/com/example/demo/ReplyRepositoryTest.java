package com.example.demo;

import com.example.demo.dto.AllDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//h2이외의 db사용시 yml에 설정된 db설정 사용
//@Transactional //테스트후 테스트한 내용을 취소하고 싶은 경우
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private Bbs2Repository bbs2Repository;

    @Autowired
    private Member2Repository member2Repository;

    private Member2 testMember;
    private Bbs2 testBbs;
    private Reply testReply;

    @BeforeEach
    void setUp() {
        // 테스트 멤버 저장
        testMember = new Member2();
        testMember.setMemberId("testUser");
        testMember.setName("Test User");
        testMember.setPw("testPw");
        testMember = member2Repository.save(testMember);

        // 테스트 게시글 저장
        testBbs = new Bbs2();
        testBbs.setTitle("Test Bbs");
        testBbs.setWriter(testMember.getMemberId());
        testBbs.setContent("Test Bbs");
        testBbs = bbs2Repository.save(testBbs);

        // 테스트 댓글 저장
        testReply = new Reply();
        testReply.setContent("Test Reply");
        testReply.setWriter("testUser");
        testReply.setBbsNo(testBbs.getBbsNo());
        testReply = replyRepository.save(testReply);
    }


    // test완료. 250131
    @Test
    void testFindByWriter() {
        // when
        List<AllDto> results = replyRepository.findByWriter("testUser");

        System.out.println("======================================");
        results.forEach(System.out::println);
        System.out.println("======================================");

        // then
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getReplyNo()).isEqualTo(testReply.getReplyNo());
        assertThat(results.get(0).getContent()).isEqualTo(testReply.getContent());
        assertThat(results.get(0).getWriter()).isEqualTo(testReply.getWriter());
        assertThat(results.get(0).getTitle()).isEqualTo(testBbs.getTitle());
        assertThat(results.get(0).getName()).isEqualTo(testMember.getName());
    }
}