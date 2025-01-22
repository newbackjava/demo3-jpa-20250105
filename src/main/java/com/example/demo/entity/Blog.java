package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가!
    private Long blogId;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 250)
    private String mail;

    @Column(nullable = false, length = 500)
    private String img;
    private String content;

    //이미지 ocr 분석 결과
    private String content2;
}