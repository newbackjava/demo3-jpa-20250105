package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat") // 테이블 이름 명시
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    @Column(nullable = false) // NotNull에 대응
    private String message;

    @Column(nullable = false) // NotNull에 대응
    private String loginId;

    @Column(nullable = false) // NotNull에 대응
    private LocalDateTime createdAt;
}
