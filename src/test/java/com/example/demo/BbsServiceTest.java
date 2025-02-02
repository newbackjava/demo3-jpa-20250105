package com.example.demo;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs;
import com.example.demo.entity.Member;
import com.example.demo.repository.Bbs2Repository;
import com.example.demo.repository.BbsRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.BbsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // 각 테스트가 끝나면 자동으로 롤백 (데이터베이스 정리)
class BbsServiceTest {

    @Autowired
    private BbsService bbsService;

    @Autowired
    private BbsRepository bbsRepository;

    @Autowired
    private Bbs2Repository bbs2Repository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;
    private Bbs bbs;

    @BeforeEach
    void setUp() {
        // 1. 테스트용 회원 생성 및 저장
        member = Member.builder()
                .memberId("testuser")
                .pw("password123")
                .name("Test User")
                .tel("123-456-7890")
                .build();
        member = memberRepository.save(member);

        // 2. 테스트용 게시글 생성 및 저장
        bbs = Bbs.builder()
                .title("Test Post")
                .content("This is a test post.")
                .member(member)
                .build();
        bbs = bbsRepository.save(bbs);
    }

    /**
     * 특정 작성자(memberId)가 작성한 모든 게시글을 조회하는 테스트
     */
    // test통과 250131
    @Test
    void testFindByMemberId() {
        List<MemberBbsDto> result = bbsService.findByMemberId("testuser");

        System.out.println("====================================");
        result.forEach(System.out::println);
        System.out.println("====================================");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());  // 게시글이 1개만 존재해야 함
        assertEquals(bbs.getTitle(), result.get(0).getTitle());
    }

    /**
     * JPQL을 사용하여 특정 게시글(BbsNo)과 작성자 정보를 DTO로 반환하는 테스트
     */
    // test통과 250131
    @Test
    void testFindBbsWithMember() {
        MemberBbsDto result = bbsService.findBbsWithMember(bbs.getBbsNo());

        System.out.println("====================================");
        System.out.println(result);
        System.out.println("====================================");

        assertNotNull(result);
        assertEquals(bbs.getBbsNo(), result.getBbsNo());
        assertEquals(bbs.getTitle(), result.getTitle());
        assertEquals(bbs.getContent(), result.getContent());
        assertEquals(member.getMemberId(), result.getMemberId());
        assertEquals(member.getName(), result.getName());
    }

    /**
     * JOIN FETCH를 사용하여 특정 게시글(BbsNo)과 작성자 정보를 조회하는 테스트
     */
    // test통과 250131
    @Test
    void testFindBbsWithMemberFetch() {
        MemberBbsDto result = bbsService.findBbsWithMemberFetch(3L);

        System.out.println("====================================");
        System.out.println(result);
        System.out.println("====================================");

        assertNotNull(result);
        assertEquals(bbs.getBbsNo(), result.getBbsNo());
        assertEquals(bbs.getTitle(), result.getTitle());
        assertEquals(bbs.getContent(), result.getContent());
        assertEquals(member.getMemberId(), result.getMemberId());
        assertEquals(member.getName(), result.getName());
    }

    /**
     * Native Query를 사용하여 특정 게시글(BbsNo)과 작성자 정보를 조회하는 테스트
     */
    // test통과, 20250131
    @Test
    void testFindBbsWithMemberNative() {
        MemberBbsDto result = bbsService.findBbsWithMemberNative(3L);

        System.out.println("====================================");
        System.out.println(result);
        System.out.println("====================================");

        assertNotNull(result);
        assertEquals(bbs.getBbsNo(), result.getBbsNo());
        assertEquals(bbs.getTitle(), result.getTitle());
        assertEquals(bbs.getContent(), result.getContent());
        assertEquals(member.getMemberId(), result.getMemberId());
        assertEquals(member.getName(), result.getName());
    }
}
