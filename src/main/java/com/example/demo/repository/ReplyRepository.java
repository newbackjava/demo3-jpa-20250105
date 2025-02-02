package com.example.demo.repository;

import com.example.demo.dto.AllDto;
import com.example.demo.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository
        extends JpaRepository<Reply, Long> {


    /// /////네이티브에서 테이블명 대문자로 시작해도 되나? OK
    /// TDD로 테스트함.
    @Query("""
    SELECT new com.example.demo.dto.AllDto(
            r.replyNo,
            r.content,
            r.writer,
            b.title,
            m.name
        )
        FROM Reply r
        JOIN Bbs2 b ON r.bbsNo = b.bbsNo
        JOIN Member2 m ON b.writer = m.memberId
        WHERE m.memberId = :memberId
        ORDER BY r.bbsNo
    """)
    List<AllDto> findByWriter(@Param("memberId") String memberId);
}

/* *********************** JPQL ****************************
@Query(value = """
        SELECT
            r.replyNo AS replyNo,
            r.content AS content,
            r.writer AS writer,
            b.title AS title,
            m.name AS name
        FROM Reply r
        JOIN Bbs b ON r.bbsNo = b.bbsNo
        JOIN Member m ON b.writer = m.memberId
        WHERE m.memberId = :memberId
        ORDER BY r.bbsNo
    """)
public List<AllDTO> findByWriter(@Param("memberId") String memberId);
}
********************************************************** */


