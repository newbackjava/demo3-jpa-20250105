package com.example.demo;

import com.example.demo.dto.AllDto;
import com.example.demo.entity.Reply;
import com.example.demo.service.ReplyService;
import com.example.demo.dto.AllDto;
import com.example.demo.service.ReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

//Mock을 유지하면 컨트롤러만 테스트
//Mock을 제거하면 컨트롤러 + 서비스까지 테스트 🚀

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
class ReplyControllerTest {

    @LocalServerPort
    private int port = 8888;

    @Autowired
    private TestRestTemplate restTemplate;

    // @MockBean 제거 → 실제 서비스 실행
    @Autowired
    private ReplyService replyService;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 추가 (실제 DB에 저장될 수 있음)
        Reply reply = new Reply();
        reply.setBbsNo(3L);
        reply.setContent("This is a test reply");
        reply.setWriter("testUser");
        replyService.save(reply);
    }


    // test완료. 250131
    @Test
    void testGetBbsDetails() {
        String url = "http://localhost:" + port + "/reply/all?writer=testUser";
        ResponseEntity<AllDto[]> response
                = restTemplate.postForEntity(url, null, AllDto[].class);

        System.out.println("Response: " + response.getBody()[0]);
    }


    // test완료. 250131
    @Test
    void testGetBbsDetails2() {
        String url = "http://localhost:" + port + "/reply/all?writer=testUser";

        ResponseEntity<AllDto[]> response
                = restTemplate.postForEntity(url, null, AllDto[].class);

        System.out.println("=================================================");
        for (AllDto dto : response.getBody()) {
            System.out.println(dto);
        }
        System.out.println("=================================================");
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotEmpty();

        AllDto dto = response.getBody()[0];
        System.out.println("############################");
        System.out.println(dto.toString());
        System.out.println("############################");
        assertThat(dto.getReplyNo()).isEqualTo(1L);
        assertThat(dto.getContent()).isEqualTo("Test Content");
        assertThat(dto.getWriter()).isEqualTo("testUser");
        assertThat(dto.getTitle()).isEqualTo("Test Title");
        assertThat(dto.getName()).isEqualTo("Test Name");
    }
}
