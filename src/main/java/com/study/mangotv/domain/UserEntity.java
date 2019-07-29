package com.study.mangotv.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srl", nullable = false)
    long srl;

    @Column(name = "id", nullable = false)
    String id;

    String password;

    String nickname;

    String email;

    String status;

    String iconUrl;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    LocalDateTime loginDate;
}