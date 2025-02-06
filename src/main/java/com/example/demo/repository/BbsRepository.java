package com.example.demo.repository;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//join한 결과를 MemberBbsDTO에 넣는 경우, JPQL(entity이름으로 써줌, Member, Bbs)
    ///  JPQL에서는 Entity이름으로 써야하고, native query에서는 Table이름으로 써야함.
    ///  ===> 동일하게 만들어서 어떤 것이 와도 상관없게 코드해줄 것.
    /// ===> interface에서는 무조건 테이블명(엔티티명)을 Member, Bbs등 낙타표기법으로 써줄 것.
    /// 인텔리제이에서 테이블명 인식못하면 오른쪽 dbms연결해줄 것!!
    /// join한 결과를 넣을 때는 아래와 같이 new객체생성한 후 생성자에 넣어줌.
    @Repository
    public interface BbsRepository
            extends JpaRepository<Bbs, Long> {

        // /////////////////////////////////////////////////////////////////
        // ** JOIN FETCH를 사용하는 경우, Entity에 반드시 관계성 명시해야함.
        // @ManyToOne, @OneToMany
        // return type은 many에 해당하는 Entity Type이 되어야함.
        // 예) Bbs
        // /////////////////////////////////////////////////////////////////

//        private Long bbsNo;
//        private String title;
//        private String content;
//        private String memberId;
//        private String name;
        @Query("""
            SELECT new com.example.demo.dto.MemberBbsDto
                (b.bbsNo, b.title, b.content, b.member.memberId, b.member.name)
                FROM Bbs b JOIN b.member WHERE b.bbsNo = :bbsNo
            """)
        MemberBbsDto findBbsWithMember(@Param("bbsNo") Long bbsNo);


        /////// JOIN FETCH를 사용하여 Bbs와 Member를 한번에 가져오는 쿼리
        @Query("""
            SELECT  b
                FROM Bbs b JOIN FETCH b.member WHERE b.bbsNo = :bbsNo
            """)
        Bbs findBbsWithMemberFetch(@Param("bbsNo") Long bbsNo);


       // 특정 memberId로 작성된 모든 게시글을 가져오는 쿼리
        //dto에 넣는 경우 fetch필요 없음.
        @Query("""
            SELECT new com.example.demo.dto.MemberBbsDto
                (b.bbsNo, b.title, b.content, m.memberId, m.name)
                FROM Bbs b JOIN b.member m WHERE m.memberId = :memberId
            """)
        List<MemberBbsDto> findByMemberId(@Param("memberId") String memberId);
    }

/// ///////////// entity(vo)가 아니라
// ////////////// native query에서는 파라메터 하나씩 전달해야함. //////////////////////////
/*
@Repository
public interface BbsRepository extends JpaRepository<Bbs, Long> {

    @Query(value = """
        SELECT
            b.no AS bbsNo,
            b.title AS bbsTitle,
            b.writer AS bbsWriter,
            m.name AS memberName
        FROM board b
        JOIN member m ON b.writer = m.memberId
        WHERE b.no = :bbsNo AND b.writer = :writer
    """, nativeQuery = true)
    List<AllDTO> findAllBbsNoMemberId(@Param("bbsNo") long bbsNo, @Param("writer") String writer);
}
*/
////////////////////////////////////////////////////////////////////////////////////////////////////////