package com.example.batchstudy_1.application;

import com.example.batchstudy_1.EmailProvider;
import com.example.batchstudy_1.batch.ItemWriter;
import com.example.batchstudy_1.customer.Customer;
import com.example.batchstudy_1.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DormantBatchItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    @Override
    public void wirte(Customer item) {
        customerRepository.save(item);
        //휴면 계정 메일 전송
        emailProvider.sendEmail(item.getEmail(), "휴면 전환 안내", "휴면 전환합니다 싫으면 로그인 ㄱㄱ");
    }
}
