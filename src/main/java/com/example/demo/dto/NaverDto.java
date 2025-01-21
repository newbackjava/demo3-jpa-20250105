package com.example.demo.dto;

import groovy.transform.ToString;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.entity.Naver}
 */
@Value
@ToString
public class NaverDto{
    String email;
    String password;
    String nickname;
    String gender;
    String profile_image;
}


