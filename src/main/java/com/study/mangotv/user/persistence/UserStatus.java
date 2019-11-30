package com.study.mangotv.user.persistence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_status")
@Data
public class UserStatus {
    @Id
    @Column(name = "status", length = 10)
    private String status;
}