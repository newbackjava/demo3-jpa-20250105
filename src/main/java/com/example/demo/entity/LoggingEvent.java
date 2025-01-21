package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "logging_event")
public class LoggingEvent {
    @Id
    @Column(name = "event_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "timestmp", nullable = false)
    private Instant timestmp;

    @Size(max = 50)
    @NotNull
    @Column(name = "level_string", nullable = false, length = 50)
    private String levelString;

    @Size(max = 255)
    @NotNull
    @Column(name = "logger_name", nullable = false)
    private String loggerName;

    @NotNull
    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    @Size(max = 255)
    @Column(name = "thread_name")
    private String threadName;

}