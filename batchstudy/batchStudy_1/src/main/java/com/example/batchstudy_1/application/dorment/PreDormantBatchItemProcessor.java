package com.example.batchstudy_1.application.dorment;

import com.example.batchstudy_1.batch.ItemProcessor;
import com.example.batchstudy_1.customer.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PreDormantBatchItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) {

        final LocalDate targetDate = LocalDate.now()
                .minusDays(365)
                .plusDays(7);

        if (targetDate.equals(customer.getLoginAt().toLocalDate())) {
            return customer;
        } else {
            return null;
        }

    }

}
