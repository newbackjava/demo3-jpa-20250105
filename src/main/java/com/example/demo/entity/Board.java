package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "no", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Size(max = 45)
    @NotNull
    @Column(name = "content", nullable = false, length = 45)
    private String content;

    @Size(max = 45)
    @NotNull
    @Column(name = "writer", nullable = false, length = 45)
    private String writer;

    @Size(max = 255)
    @Column(name = "img")
    private String img;

}