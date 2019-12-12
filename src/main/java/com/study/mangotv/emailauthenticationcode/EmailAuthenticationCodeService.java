package com.study.mangotv.emailauthenticationcode;

import com.study.mangotv.common.model.CodeMessageResponse;
import com.study.mangotv.common.util.DateTimeUtil;
import com.study.mangotv.emailauthenticationcode.model.EmailAuthenticationCodeRequest;
import com.study.mangotv.persistence.emailauthenticationcode.EmailAuthenticationCodeEntity;
import com.study.mangotv.persistence.emailauthenticationcode.EmailAuthenticationCodeRepository;
import com.study.mangotv.persistence.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailAuthenticationCodeService {

    @Autowired
    private EmailAuthenticationCodeRepository emailAuthenticationCodeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailAuthenticationCodeMailClient emailAuthenticationCodeMailClient;
    @Autowired
    private EmailAuthenticationCodeJwtProvider emailAuthenticationCodeJwtProvider;

    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    public static final long EXPIRE_TIME = 5 * 60L;   // 5 minutes
    public static final int GENERATED_CODE_LENGTH = 15;

    public CodeMessageResponse generateCode(EmailAuthenticationCodeRequest emailAuthenticationCodeRequest) {
        userRepository.findByEmail(emailAuthenticationCodeRequest.getEmail())
                .ifPresent(v -> {
                    throw new RuntimeException("이미 사용중인 이메일입니다.");
                });

        String email = emailAuthenticationCodeRequest.getEmail();
        LocalDateTime now = LocalDateTime.now();

        CodeMessageResponse codeMessageResponse = emailAuthenticationCodeRepository.findById(email)
                .filter(entity -> !this.isExpirationTimeout(now, entity.getCreateDate().plusSeconds(EXPIRE_TIME)))
                .map(entity -> CodeMessageResponse.builder().code(1001).message("failure").build())
                .orElse(CodeMessageResponse.builder().code(1000).message("success").build());

        if (codeMessageResponse.getCode() == 1000) {
            String code = RandomStringUtils.randomAlphanumeric(GENERATED_CODE_LENGTH);
            System.out.println("Generated Code: " + code);
            // TODO: 2019-12-11 이메일 전송 코드 주석 풀기
//            emailAuthenticationCodeMailSender.sendMail(email, code);
            emailAuthenticationCodeRepository.save(new EmailAuthenticationCodeEntity(email, code, now));
        }

        return codeMessageResponse;
    }

    public CodeMessageResponse matchCode(String email, String code) {
        LocalDateTime now = LocalDateTime.now();

        CodeMessageResponse codeMessageResponse = emailAuthenticationCodeRepository.findById(email)
                .filter(entity ->
                        entity.getCode().equals(code)
                                && !this.isExpirationTimeout(now, entity.getCreateDate().plusSeconds(EXPIRE_TIME))
                )
                .map(entity -> CodeMessageResponse.builder()
                        .code(1000)
                        .message(this.createJwt(entity.getEmail(), entity.getCreateDate()))
                        .build()
                )
                .orElse(CodeMessageResponse.builder().code(1001).message("failure").build());

        if (codeMessageResponse.getCode() == 1000) {
            emailAuthenticationCodeRepository.deleteById(email);
        }

        return codeMessageResponse;
    }

    private boolean isExpirationTimeout(LocalDateTime targetDateTime, LocalDateTime baseDateTime) {
        return dateTimeUtil.timeGapToSeconds(targetDateTime, baseDateTime) <= 0;
    }

    private String createJwt(String email, LocalDateTime createDate) {
        return emailAuthenticationCodeJwtProvider.createToken(email, createDate);
    }
}
