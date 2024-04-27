package com.tai.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = "ahi123";
        String encodePass = encoder.encode(rawPass);
        System.out.println(encodePass);
    }
}
