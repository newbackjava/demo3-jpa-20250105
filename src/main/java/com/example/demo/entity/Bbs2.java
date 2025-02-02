package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Bbs2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bbsNo;

    @NotNull
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Size(max = 45)
    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

    @Size(max = 300)
    @Column(name = "writer")
    private String writer;
}
