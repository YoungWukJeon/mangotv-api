package com.study.mangotv.user.persistence;

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

    @Column(name = "nickname", length = 20, nullable = false, unique = true)
    private String nickname;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "status", nullable = false)
    private UserStatus userStatus;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "login_date")
    private LocalDateTime loginDate;
}
