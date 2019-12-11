package com.study.mangotv.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailAuthenticationCodeMailClient {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    public void sendMail(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(recipient);
            messageHelper.setSubject("[MangoTv] 가입을 위한 이메일 인증 코드");
            String content = this.build(message);
            messageHelper.setText(content, true);
        };
        javaMailSender.send(messagePreparator);
    }

    private String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("email-authentication-code-template", context);
    }
}
