package com.example.batchstudy_1.application.dormant;

import com.example.batchstudy_1.BatchStudy1Application;
import com.example.batchstudy_1.EmailProvider;
import com.example.batchstudy_1.application.dorment.PreDormantBatchItemWriter;
import com.example.batchstudy_1.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = BatchStudy1Application.class)
class PreDormantBatchItemWriterTest {

    private PreDormantBatchItemWriter preDormantBatchItemWriter;

    @Test
    @DisplayName("1주일 뒤에 휴면계정전환 예정자라고 이메일을 전송해야한다.")
    void test1() {

        // given
        final EmailProvider mockEmailProvider = mock(EmailProvider.class);
        this.preDormantBatchItemWriter = new PreDormantBatchItemWriter(mockEmailProvider);

        final Customer customer = new Customer("minsoo", "minsoo@fastcampus.com");

        // when
        preDormantBatchItemWriter.write(customer);

        // then

        verify(mockEmailProvider, atLeastOnce()).sendEmail(any(), any(), any());

    }

}