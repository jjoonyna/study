package com.example.batchstudy_1.application.dorment;

import com.example.batchstudy_1.EmailProvider;
import com.example.batchstudy_1.batch.JobExecution;
import com.example.batchstudy_1.batch.JobExecutionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;
    @Override
    public void beforeJob(JobExecution execution){
        execution.setStatus(execution.getStatus());
        execution.setStartTime(LocalDateTime.now());
    }

    @Override
    public void afterJob(JobExecution  execution){
        emailProvider.sendEmail("admin@test.com","배치 완료","배치수행 완료했습니다 status: "+execution.getStatus());
    }
}
