package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    private String memberId;

    @Column(nullable = false)
    private String pw;

    @Column
    private String name;

    @Column
    private String tel;

    @OneToMany(mappedBy = "member",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Bbs> bbsList; // Member가 작성한 게시글 목록

}
