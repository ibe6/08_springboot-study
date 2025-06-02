package com.ibe6.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class BCryptPasswordEncoderTest {

    /*
        ## 암호화 ##
        1. 평문을 다른 사람드링 알아볼 수 없게 암호문으로 변경하는 과정
        2. DB 같은 저장소에 개인정보로 보호해야 되는 값들은 암호문으로 보관해야됨
     */


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void 평문_to_암호문_테스트(){
        String rawPassword = "pass02"; // 평문
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("평문: " + rawPassword);
        System.out.println("암호문: " + encodedPassword);
    }

}
