package com.study.mangotv.persistence.emailauthenticationcode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "email_authentication_code")
public class EmailAuthenticationCodeEntity {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "code", length = 40, nullable = false)
    private String code;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    public EmailAuthenticationCodeEntity(String email, String code, LocalDateTime createDate) {
        this.email = email;
        this.code = code;
        this.createDate = createDate;
    }
}
