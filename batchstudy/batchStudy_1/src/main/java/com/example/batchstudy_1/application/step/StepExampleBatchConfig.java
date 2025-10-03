package com.example.batchstudy_1.application.step;

import com.example.batchstudy_1.batch.Job;
import com.example.batchstudy_1.batch.Step;
import com.example.batchstudy_1.batch.StepJobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepExampleBatchConfig {

    @Bean
    public Job stepExampleBatchJob(
            Step step1,
            Step step2,
            Step step3
    ) {
        return new StepJobBuilder()
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1() {
        return new Step(() -> System.out.println("Step1"));
    }

    @Bean
    public Step step2() {
        return new Step(() -> System.out.println("Step2"));
    }

    @Bean
    public Step step3() {
        return new Step(() -> System.out.println("Step3"));
    }

}