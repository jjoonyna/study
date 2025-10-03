package com.example.batchstudy_1.batch;

import com.example.batchstudy_1.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public interface ItemProcessor<I, O> {

    O process(I item);

}
