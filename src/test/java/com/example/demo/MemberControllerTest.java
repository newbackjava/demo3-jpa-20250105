package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET요청 테스트")
    public void testMemberEndpoint() throws Exception {
        mockMvc.perform(get("/member/member"))  // /member/member로 GET 요청
                .andExpect(status().isOk())  // 상태 코드 200 검증
                .andExpect(content().string("Expected Response")); // 응답 내용 검증 (필요 시 수정)
    }

    @Test
    public void testReadEndpoint() throws Exception {
        mockMvc.perform(post("/member/read")
                        .param("id", "apple")) // 요청 파라미터 id=apple
                .andExpect(status().isOk())  // HTTP 상태 코드 200 확인
                .andExpect(content().string("Expected Response")); // 예상 응답 내용 검증 (필요시 수정)
    }

}