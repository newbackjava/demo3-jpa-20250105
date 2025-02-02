package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberBbsDto {
    private Long bbsNo;
    private String title;
    private String content;
    private String memberId;
    private String name;
}
