package com.example.batchstudy_1.application;

import com.example.batchstudy_1.batch.ItemProcessor;
import com.example.batchstudy_1.batch.ItemReader;
import com.example.batchstudy_1.batch.ItemWriter;
import com.example.batchstudy_1.batch.Tasklet;
import com.example.batchstudy_1.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DormantBatchTasklet<I, O> implements Tasklet {

    private final ItemReader<Customer> itemReader;
    private final ItemWriter<Customer> itemWriter;
    private final ItemProcessor<Customer, Customer> itemProcessor;

    @Override
    public void execute() {

        while (true) {
            //유저 조회
            final Customer read = itemReader.read();
            if(read == null) {
                break;
            }

            //휴면 계정 대상 추출 및 변환
            final Customer process = itemProcessor.process(read);
            if(process == null) {
                continue;
            }

            //휴면 계정 상태 변환
            itemWriter.wirte(process);



        }
    }
}
