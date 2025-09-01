package com.example.batchstudy_1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailProvider {
    void sendEmail(String emailAddress, String title, String body) {
        log.info("{} email 전송 완료 {} : {}" , emailAddress, title, body);
    }
}
