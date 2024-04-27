package com.tai.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;


public class PasswordEncoderTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = "ahi123";
        String encodePass = encoder.encode(rawPass);
        System.out.println(encodePass);
    }
}
