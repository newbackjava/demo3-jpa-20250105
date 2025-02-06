package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member2 {

    @Id
    private String memberId;

    @Column(nullable = false)
    private String pw;

    @Column
    private String name;

    @Column
    private String tel;
}
