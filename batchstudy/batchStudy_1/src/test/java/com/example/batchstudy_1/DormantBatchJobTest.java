package com.example.batchstudy_1;

import com.example.batchstudy_1.batch.BatchStatus;
import com.example.batchstudy_1.batch.JobExcution;
import com.example.batchstudy_1.customer.Customer;
import com.example.batchstudy_1.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BatchStudy1Application.class)
class DormantBatchJobTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    @Mock
    private EmailProvider emailProvider;

    @Autowired
    private DormantBatchJob dormantBatchJob;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
    }
    @Test
    @DisplayName("로그인 시간이 1년 경과한 고객 3명, 일년 이내 로그인한 고객이 5명일때 3명의 고객이 휴면 전환 대상")
    void execute() {
        saveCustomer(366);
        saveCustomer(366);
        saveCustomer(366);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);

        final JobExcution result = dormantBatchJob.execute();

        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();


        assertEquals(3,dormantCount);
        assertEquals(BatchStatus.FINISHED,result.getStatus());
    }

    @Test
    @DisplayName("고객이 열명 있지만 모두 다 휴면 전환 대상이 아닐때 휴면 전환 대상은 0명")
    void execute2() {
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);

        final JobExcution result = dormantBatchJob.execute();

        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();


        assertEquals(0,dormantCount);
        assertEquals(BatchStatus.FINISHED,result.getStatus());
    }

    @Test
    @DisplayName("고객이 없는 경우에도 배치는 정상 작동 해야한다")
    void execute3() {
        final JobExcution result = dormantBatchJob.execute();

        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();

        assertEquals(0,dormantCount);
        assertEquals(BatchStatus.FINISHED,result.getStatus());
    }

    @Test
    @DisplayName("배치가 실패하면 BatchStatus는 FAILED 반환")
    void execute4() {
        final DormantBatchJob dormantBatchJob = new DormantBatchJob(null, emailProvider);
        final JobExcution result = dormantBatchJob.execute();
        assertEquals(BatchStatus.FAILED, result.getStatus());
    }

    private void saveCustomer(long loginMinusDays) {
        final String uuid = UUID.randomUUID().toString();
        final Customer test = new Customer(uuid, uuid+"@test.com");
        test.setLoginAt(LocalDateTime.now().minusDays(loginMinusDays));
        customerRepository.save(test);
    }
}