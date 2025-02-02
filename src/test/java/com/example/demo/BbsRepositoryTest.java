package com.example.demo;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs;
import com.example.demo.entity.Member;
import com.example.demo.repository.BbsRepository;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional (넣으면 cud작업이 취소됨)
//@DataJpaTest //Transactional을 포함하고 있음.
public class BbsRepositoryTest {
// TDD(Test-Driven Development) 방식으로 BbsRepository의 findBbsWithMemberFetch() 메서드를 테스트하는 JUnit 코드입니다.
//
//TDD의 기본 흐름:
//
//테스트 코드 작성 (Red): 원하는 기능을 먼저 테스트로 작성.
//기능 구현 (Green): 테스트를 통과하는 최소한의 코드 작성.
//리팩토링 (Refactor): 코드 정리 및 개선.

    @Autowired
    private BbsRepository bbsRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;
    private Bbs bbs;

//    @BeforeEach
//    public void setUp() {
//        // 초기 데이터 세팅 (DB에 실제로 저장될 객체들)
//        member = new Member();
//        member.setMemberId("testUser");
//        member.setPw("password123");
//        member.setName("Test User");
//        member.setTel("123-456-7890");
//
//        // member 저장
//        member = memberRepository.save(member);
//
//        // Bbs 생성 및 member 연결
//        bbs = new Bbs();
//        bbs.setTitle("Test Post");
//        bbs.setContent("This is a test post.");
//        bbs.setMember(member);
//
//        // 게시글 저장
//        bbs = bbsRepository.save(bbs);
//    }


    // test통과 250131
    @Test
    public void testFindBbsWithMember() {
        // ✅ 테스트:  findBbsWithMember() 메서드를 통해 게시글과 작성자 정보가 제대로 반환되는지 확인
        MemberBbsDto result = bbsRepository.findBbsWithMember(3L);

        System.out.println("====================================");
        System.out.println(result);
        System.out.println("====================================");

        // ✅ 검증
        assertNotNull(result);
        assertEquals(bbs.getBbsNo(), result.getBbsNo());
        assertEquals(bbs.getTitle(), result.getTitle());
        assertEquals(bbs.getContent(), result.getContent());
        assertEquals(member.getMemberId(), result.getMemberId());
        assertEquals(member.getName(), result.getName());
    }

    // test통과 250131
    @Test
    public void testFindByMemberId() {
        // ✅ 테스트: 특정 memberId로 작성된 모든 게시글을 가져오는지 확인
        List<MemberBbsDto> result = bbsRepository.findByMemberId("testUser");

        System.out.println("====================================");
        result.forEach(System.out::println);
        System.out.println("====================================");

        // ✅ 검증
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());  // 게시글 하나만 존재해야 함
        assertEquals(bbs.getTitle(), result.get(0).getTitle());
    }



    // test통과 250131
    @Test
    public void testFindBbsWithMemberFetch() {
        // ✅ findBbsWithMemberFetch()를 통해 게시글과 작성자 정보가 조회되는지 확인
        Bbs result = bbsRepository.findBbsWithMemberFetch(3L);

        System.out.println("====================================");
        System.out.println("게시글 번호: " + result.getBbsNo());
        System.out.println("제목: " + result.getTitle());
        System.out.println("내용: " + result.getContent());
        System.out.println("작성자 ID: " + result.getMember().getMemberId());
        System.out.println("작성자 이름: " + result.getMember().getName());
        System.out.println("====================================");

        // ✅ 검증
        assertNotNull(result);
        assertEquals(3L, result.getBbsNo());
        assertEquals("Title 3", result.getTitle());
        assertEquals("Content of the post 3", result.getContent());
        assertEquals("testUser", result.getMember().getMemberId());
        assertEquals("Test User", result.getMember().getName());
    }
}
