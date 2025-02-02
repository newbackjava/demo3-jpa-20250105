package com.example.demo;

import com.example.demo.dto.AllDto;
import com.example.demo.entity.Reply;
import com.example.demo.service.ReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional
class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @BeforeEach
    void setUp() {
        Reply reply = new Reply();
        reply.setBbsNo(4L);
        reply.setContent("Test Reply");
        reply.setWriter("testUser");
        reply.setContent("Test Reply");
        replyService.save(reply);
    }


    // test완료. 250131
    @Test
    void testFindRepliesByUser() {
        List<AllDto> replies = replyService.getAll("testUser");
        System.out.println("=========================");
        
        //반복하면서 하나씩 꺼내서 람다식 적용
        replies.forEach(System.out::println);
        System.out.println("=========================");
        assertThat(replies).isNotEmpty();
    }
}
