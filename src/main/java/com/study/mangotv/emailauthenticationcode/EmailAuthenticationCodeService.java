package com.study.mangotv.emailauthenticationcode;

import com.study.mangotv.common.config.jwt.UserRegistrationJwtProvider;
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
    private UserRegistrationJwtProvider userRegistrationJwtProvider;

    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    public static final long EXPIRE_TIME = 5 * 60L;   // 5 minutes
    public static final int GENERATED_CODE_LENGTH = 15;

    public CodeMessageResponse generateCode(EmailAuthenticationCodeRequest emailAuthenticationCodeRequest) {
        // TODO: 2019-12-13 Email Validation Check
        userRepository.findByEmail(emailAuthenticationCodeRequest.getEmail())
                .ifPresent(v -> {
                    throw new RuntimeException("이미 사용중인 이메일입니다.");
                });

        String email = emailAuthenticationCodeRequest.getEmail();
        LocalDateTime now = LocalDateTime.now();

        String code = RandomStringUtils.randomAlphanumeric(GENERATED_CODE_LENGTH);  // deprecated
        System.out.println("Generated Code: " + code);  // deprecated
        // TODO: 2019-12-13 클라이언트에서 개발편의성을 위해 성공 메시지에 랜덤code값 내려주기(위에 2줄은 나중에 제거)
        CodeMessageResponse codeMessageResponse = emailAuthenticationCodeRepository.findById(email)
                .filter(entity -> !this.isExpirationTimeout(now, entity.getCreateDate().plusSeconds(EXPIRE_TIME)))
                .map(entity -> CodeMessageResponse.builder().code(1001).message("failure").build())
                .orElse(CodeMessageResponse.builder().code(1000).message(code).build());

        if (codeMessageResponse.getCode() == 1000) {
//            String code = RandomStringUtils.randomAlphanumeric(GENERATED_CODE_LENGTH);
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
            // TODO: 2019-12-13 올바른 인증코드를 입력했을 때, db에서 바로 지워버릴지 고민해봐야함... 왜냐하면 동시성 문제가 있을 수 있음
            emailAuthenticationCodeRepository.deleteById(email);
        }

        return codeMessageResponse;
    }

    private boolean isExpirationTimeout(LocalDateTime targetDateTime, LocalDateTime baseDateTime) {
        return dateTimeUtil.timeGapToSeconds(targetDateTime, baseDateTime) <= 0;
    }

    private String createJwt(String email, LocalDateTime createDate) {
        return userRegistrationJwtProvider.createToken(email, createDate);
    }
}
