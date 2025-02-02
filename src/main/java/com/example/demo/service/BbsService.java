package com.example.demo.service;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs;
import com.example.demo.entity.Bbs2;
import com.example.demo.repository.Bbs2Repository;
import com.example.demo.repository.BbsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BbsService {

    private final BbsRepository bbsRepository;
    private final Bbs2Repository bbs2Repository;

    /**
     * 특정 작성자(memberId)가 작성한 모든 게시글을 조회
     */
    @Transactional(readOnly = true)
    public List<MemberBbsDto> findByMemberId(String writer) {
        return bbsRepository.findByMemberId(writer);
    }

    /**
     * JPQL을 사용하여 특정 게시글(BbsNo)과 작성자 정보를 DTO로 반환
     */
    @Transactional(readOnly = true)
    public MemberBbsDto findBbsWithMember(Long bbsNo) {
        return bbsRepository.findBbsWithMember(bbsNo);
    }

    /**
     * JOIN FETCH를 사용하여 특정 게시글(BbsNo)과 작성자 정보를 조회
     * 반환값이 `Bbs` 엔티티이므로 DTO 변환이 필요함
     */
    @Transactional(readOnly = true)
    public MemberBbsDto findBbsWithMemberFetch(Long bbsNo) {
        Bbs bbs = bbsRepository.findBbsWithMemberFetch(bbsNo);
        if (bbs == null) return null;

        MemberBbsDto memberBbsDto = MemberBbsDto.builder()
                .bbsNo(bbs.getBbsNo())
                .title(bbs.getTitle())
                .content(bbs.getContent())
                .memberId(bbs.getMember().getMemberId())
                .name(bbs.getMember().getName())
                .build();

        return memberBbsDto;
    }

    /**
     * Native Query를 사용하여 특정 게시글(BbsNo)과 작성자 정보를 조회
     * 반환값이 `Object[]`이므로 DTO 변환이 필요함
     */
    @Transactional(readOnly = true)
    public MemberBbsDto findBbsWithMemberNative(Long bbsNo) {
        List<Object[]> result = bbs2Repository.findBbsWithMemberNative(bbsNo);
        if (result == null) return null;
        System.out.println("==============================================");
        System.out.println(result.size());
        //System.out.println(result[1]);
        //System.out.println(result[2]);
        System.out.println("------------------------------------------");
        System.out.println(result.get(0)[0]);
        System.out.println("==============================================");

        MemberBbsDto memberBbsDto = MemberBbsDto.builder()
                .bbsNo((Long) result.get(0)[0])             // bbsNo
                .title((String) result.get(0)[1])               // title
                .content((String) result.get(0)[2])             // content
                .memberId((String) result.get(0)[3])            // memberId
                .name((String) result.get(0)[4])                // name
                .build();


        return memberBbsDto;
    }
}