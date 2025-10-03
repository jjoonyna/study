package com.example.batchstudy_1.application.dorment;

import com.example.batchstudy_1.EmailProvider;
import com.example.batchstudy_1.FakeEmailProvider;
import com.example.batchstudy_1.batch.ItemWriter;
import com.example.batchstudy_1.customer.Customer;
import com.example.batchstudy_1.customer.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchItemWriter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new FakeEmailProvider();
    }

    @Override
    public void write(Customer item) {
        customerRepository.save(item);
        //휴면 계정 메일 전송
        emailProvider.sendEmail(item.getEmail(), "휴면 전환 안내", "휴면 전환합니다 싫으면 로그인 ㄱㄱ");
    }
}
