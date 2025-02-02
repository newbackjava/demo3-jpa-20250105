package com.example.demo.repository;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs;
import com.example.demo.entity.Bbs2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface Bbs2Repository extends JpaRepository<Bbs2, Long> {

/* MemberBbsDto
    private Long bbsNo;
    private String title;
    private String content;
    private String memberId;
    private String name;
 */
    @Query("""
    SELECT new com.example.demo.dto.MemberBbsDto(
        b.bbsNo, 
        b.title, 
        b.content,
        m.memberId, 
        m.name
    )
    FROM Bbs2 b
    JOIN Member2 m
    WHERE m.memberId = :writer
""")
    Page<MemberBbsDto> findByWriter(@Param("writer") String writer, Pageable pageable);

    ///////  Native Query ===> Service단에서 dto로 매핑시켜줘야함.
    @Query(value = """
            SELECT b.bbsNo, b.title, b.content, m.memberId, m.name 
                FROM bbs2 b 
                JOIN member2 m ON b.writer = m.memberId 
                WHERE b.bbsNo = :bbsNo
            """, nativeQuery = true)
    List<Object[]> findBbsWithMemberNative(@Param("bbsNo") Long bbsNo);
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