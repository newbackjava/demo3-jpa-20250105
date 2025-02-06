package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.MemberBbsDto;
import com.example.demo.entity.Bbs2;
import com.example.demo.entity.Member2;
import com.example.demo.repository.Bbs2Repository;
import com.example.demo.repository.BbsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class Bbs2RepositoryTest {

    @Autowired
    private Bbs2Repository bbsRepository;

    private Member2 testMember;
    private Bbs2 testBbs;


}
