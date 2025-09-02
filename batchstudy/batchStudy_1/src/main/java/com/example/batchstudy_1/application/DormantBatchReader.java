package com.example.batchstudy_1.application;

import com.example.batchstudy_1.batch.ItemReader;
import com.example.batchstudy_1.customer.Customer;
import com.example.batchstudy_1.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DormantBatchReader implements ItemReader<Customer> {

    private final CustomerRepository customerRepository;
    private int pageNo = 0;

    @Override
    public Customer read() {
        final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by("id").descending());
        final Page<Customer> page = customerRepository.findAll(pageRequest);

        final Customer customer;
        if (page.isEmpty()) {
            pageNo = 0;
            return null;
        } else {
            pageNo++;
            return page.getContent().getFirst();
        }
    }
}
