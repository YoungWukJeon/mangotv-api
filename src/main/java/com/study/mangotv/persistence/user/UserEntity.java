package com.study.mangotv.persistence.user;

import com.study.mangotv.common.BaseDateTimeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user")
public class UserEntity extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srl")
    private Long srl;

    @Column(name = "id", length = 30, nullable = false, unique = true)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", length = 20, nullable = false)
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.NORMAL;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "login_date")
    private LocalDateTime loginDate;
}
