package com.example.batchstudy_1.application;

import com.example.batchstudy_1.EmailProvider;
import com.example.batchstudy_1.batch.BatchStatus;
import com.example.batchstudy_1.batch.JobExcution;
import com.example.batchstudy_1.batch.JobExecutionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;
    @Override
    public void beforeJob(JobExcution excution){
        excution.setStatus(excution.getStatus());
        excution.setStartTime(LocalDateTime.now());
    }

    @Override
    public void afterJob(JobExcution  excution){
        emailProvider.sendEmail("admin@test.com","배치 완료","배치수행 완료했습니다 status: "+excution.getStatus());
    }
}
