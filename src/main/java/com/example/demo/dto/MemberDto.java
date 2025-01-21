package com.example.demo.dto;

import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Value //생성자, private final, getter
@Builder
@ToString
public class MemberDto{
    String memberId;
    String pw;
    String name;
    String tel;
}