package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    private String rnum;            // 순위번호

    private String movieNm;         // 영화이름

    private String salesAmt;        // 매출액

    private String rankOldAndNew;   // 진입여부

    private String audiCnt;         // 관객수

}