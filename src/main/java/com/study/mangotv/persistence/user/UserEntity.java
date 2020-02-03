package com.study.mangotv.persistence.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
public class UserEntity implements UserDetails {
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

    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "login_date")
    private LocalDateTime loginDate;

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority(this.userStatus.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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
