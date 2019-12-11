package com.study.mangotv.persistence.emailauthenticationcode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthenticationCodeRepository extends JpaRepository<EmailAuthenticationCodeEntity, String> {
}
