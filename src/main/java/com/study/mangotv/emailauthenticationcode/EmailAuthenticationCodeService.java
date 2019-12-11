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

    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    public static final long EXPIRE_TIME = 5 * 60L;   // 5 minutes
    public static final int GENERATED_CODE_LENGTH = 15;

    public CodeMessageResponse generateCode(EmailAuthenticationCodeRequest emailAuthenticationCodeRequest) {
        // TODO: 2019-12-06 요청온 이메일이 이미 DB에 존재하는지 1차 확인.
        //  2차로 캐시든 어디든 아직 인증메일 요청정보가 남아있으면 새로온 요청으로 갱신s
//        if (this.isExistingEmail(emailAuthenticationCodeRequest.getEmail())) {
//            throw new RuntimeException("이미 존재하는 이메일입니다.");
//        }
        userRepository.findByEmail(emailAuthenticationCodeRequest.getEmail())
                .ifPresent(v -> new RuntimeException("이미 존재하는 이메일입니다."));

        String email = emailAuthenticationCodeRequest.getEmail();
        LocalDateTime now = LocalDateTime.now();

        CodeMessageResponse codeMessageResponse = emailAuthenticationCodeRepository.findById(email)
                .map(entity -> CodeMessageResponse.builder().code(1001).message("failure").build())
                .orElse(CodeMessageResponse.builder().code(1000).message("success").build());

        if (codeMessageResponse.getCode() == 1000) {
            String code = RandomStringUtils.randomAlphanumeric(GENERATED_CODE_LENGTH);
            // TODO: 2019-12-10 이메일로 code 발송 로직 필요 
            emailAuthenticationCodeRepository.save(new EmailAuthenticationCodeEntity(email, code, now));
        }

        return codeMessageResponse;
    }

    public CodeMessageResponse matchCode(String email, String code) {
        LocalDateTime now = LocalDateTime.now();

        // TODO: 2019-12-10 성공적으로 매치가 되면 jwt 등 토큰값 클라에 내려주기 
        CodeMessageResponse codeMessageResponse = emailAuthenticationCodeRepository.findById(email)
                .filter(entity ->
                        entity.getCode().equals(code)
                                && dateTimeUtil.timeGapToSeconds(entity.getCreateDate(), now) <= EXPIRE_TIME
                )
                .map(entity -> CodeMessageResponse.builder().code(1000).message("success").build())
                .orElse(CodeMessageResponse.builder().code(1001).message("failure").build());

        if (codeMessageResponse.getCode() == 1000) {
            emailAuthenticationCodeRepository.deleteById(email);
        }

        return codeMessageResponse;
    }

    private boolean isExistingEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
