package com.inncontrol.communications.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Message {

    // Getters y Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 9, nullable = false)
    private Long sender;

    @Column(length = 9, nullable = false)
    private Long receiver;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @Column(nullable = false)
    private String content;

}
