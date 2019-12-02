package com.study.mangotv.persistence.user;

import com.study.mangotv.common.BaseDateTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "unique_id", columnNames = "id"),
        @UniqueConstraint(name = "unique_nickname", columnNames = "nickname"),
        @UniqueConstraint(name = "unique_email", columnNames = "email")
})
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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.NORMAL;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "login_date")
    private LocalDateTime loginDate;

    @Builder
    public UserEntity(String id, String password, String nickname, String email, LocalDate birthDate, Character gender) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
