package com.example.demo.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.entity.Blog}
 */
@Data
public class BlogDto implements Serializable {
//
//    Long blogId;
    String name;
    String mail;
    String img;
    String content;
}