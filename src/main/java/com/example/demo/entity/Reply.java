package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;

    @NotNull
    @Column(nullable = false)
    private Long bbsNo; //bbs의 bbsNo

    @Size(max = 255)
    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @Size(max = 100)
    @NotNull
    @Column(name = "writer", nullable = false, length = 100)
    private String writer; //member테이블의 memberId

}