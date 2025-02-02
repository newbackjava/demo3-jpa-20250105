package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bbs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bbsNo;

    @Size(max = 45)
    @NotNull
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Size(max = 45)
    @NotNull
    @Column(name = "content", nullable = false, length = 45)
    private String content;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

    //@ManyToOne(fetch = FetchType.EAGER) //DEFAULT
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer",
            referencedColumnName = "memberId",
            nullable = false)
    private Member member;
}
