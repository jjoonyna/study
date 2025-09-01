package com.example.batchstudy_1;

import com.example.batchstudy_1.batch.BatchStatus;
import com.example.batchstudy_1.batch.JobExcution;
import com.example.batchstudy_1.customer.Customer;
import com.example.batchstudy_1.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DormantBatchJob {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public JobExcution execute() {
        final JobExcution excution = new JobExcution();
        excution.setStatus(BatchStatus.STARTED);
        excution.setStartTime(LocalDateTime.now());

        int pageNo = 0;
        try {
            while(true){
            //유저 조회
            final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by("id").descending());
            final Page<Customer> page = customerRepository.findAll(pageRequest);

            final Customer customer;
            if(page.isEmpty()){
                break;
            } else {
                pageNo++;
                customer = page.getContent().get(0);
            }
            //휴면 계정 대상 추출 및 변환
            final boolean isDormantTarget = LocalDate.now()
                    .minusDays(365)
                    .isAfter(customer.getLoginAt().toLocalDate());

            if(isDormantTarget) {
                customer.setStatus(Customer.Status.DORMANT);
            } else {
                continue;
            }
            //휴면 계정 상태 변환
            customerRepository.save(customer);

            //휴면 계정 메일 전송
            emailProvider.sendEmail(customer.getEmail(), "휴면 전환 안내", "휴면 전환합니다 싫으면 로그인 ㄱㄱ");
        }
            excution.setStatus(BatchStatus.FINISHED);
        } catch (Exception e) {
            excution.setStatus(BatchStatus.FAILED);
        }
        excution.setEndTime(LocalDateTime.now());
        emailProvider.sendEmail("admin@test.com","배치 완료","배치수행 완료했습니다 status: "+excution.getStatus());
        return excution;
    }
}
