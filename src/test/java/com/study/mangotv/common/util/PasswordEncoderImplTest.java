package com.study.mangotv.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderImplTest {
    private PasswordEncoderImpl bcryptPasswordEncoder = new PasswordEncoderImpl();

    @Test
    void Bcrypt_암호화_테스트() {
        String rawMessage = "testPass";

        String encodedMessage = bcryptPasswordEncoder.encode(rawMessage);
        System.out.println("encodedMessage: " + encodedMessage);

        assertTrue(bcryptPasswordEncoder.matches("testPass", encodedMessage));
    }
}