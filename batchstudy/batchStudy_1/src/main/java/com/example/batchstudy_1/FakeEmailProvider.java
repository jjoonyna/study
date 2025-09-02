package com.example.batchstudy_1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FakeEmailProvider implements EmailProvider {
    @Override
    public void sendEmail(String emailAddress, String title, String body) {
        log.info("{} email 전송 완료 {} : {}", emailAddress, title, body);
    }
}
