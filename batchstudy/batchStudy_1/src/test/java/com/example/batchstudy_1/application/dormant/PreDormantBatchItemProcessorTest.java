package com.example.batchstudy_1.application.dormant;

import com.example.batchstudy_1.BatchStudy1Application;
import com.example.batchstudy_1.application.dorment.PreDormantBatchItemProcessor;
import com.example.batchstudy_1.customer.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest(classes = BatchStudy1Application.class)
class PreDormantBatchItemProcessorTest {

    private PreDormantBatchItemProcessor preDormantBatchItemProcessor;

    @BeforeEach
    void setup() {
        preDormantBatchItemProcessor = new PreDormantBatchItemProcessor();
    }

    @Test
    @DisplayName("로그인 날짜가 오늘로부터 358일전이면 customer를_반환해야한다.")
    void test1() {

        // given
        final Customer customer = new Customer("minsoo", "minsoo@fastcampus.com");
        // 오늘은 2023.06.04 예정자는 2022.06.11
        customer.setLoginAt(LocalDateTime.now().minusDays(365).plusDays(7));

        // when
        final Customer result = preDormantBatchItemProcessor.process(customer);

        // then
        Assertions.assertThat(result).isEqualTo(customer);
        Assertions.assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("로그인 날짜가 오늘로부터 358일전이 아니면 null을_반환해야한다.")
    void test2() {

        // given
        final Customer customer = new Customer("minsoo", "minsoo@fastcampus.com");

        // when
        final Customer result = preDormantBatchItemProcessor.process(customer);

        // then
        Assertions.assertThat(result).isNull();

    }

}