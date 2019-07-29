package com.study.mangotv.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long srl;
    private  String id;
    private String password;
    private String nickname;
    private String email;
    private String status = "NORMAL";
    private String iconUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime loginDate;
}