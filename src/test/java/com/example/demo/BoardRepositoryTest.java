package com.example.demo;

import com.example.demo.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //취소하고 싶은 경우 넣으면 됨.
public class BoardRepositoryTest {

    @Autowired
    public BoardRepository boardRepository;

    @Test
    public void testSave() {
        boardRepository.deleteByIdAfter(1);
    }
}
