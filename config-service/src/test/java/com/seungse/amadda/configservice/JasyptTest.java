package com.seungse.amadda.configservice;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class JasyptTest {

    @Autowired
    private StringEncryptor jasyptStringEncryptor;

    @Test
    @DisplayName("암복호화")
    void jasypt_create () {
        // given
        String password = "amadda";
        // when
        String encrypt = jasyptStringEncryptor.encrypt(password);
        System.err.println(encrypt);
        // then
        String decrypt = jasyptStringEncryptor.decrypt(encrypt);
        System.err.println(decrypt);
        assertThat(decrypt).isEqualTo(password);

    }
}
