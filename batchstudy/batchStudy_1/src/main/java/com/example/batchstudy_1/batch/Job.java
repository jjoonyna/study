package com.example.batchstudy_1.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Job {
    private final Tasklet tasklet;
    private final JobExecutionListener jobExcution;

    public JobExcution execute() {

        final JobExcution excution = new JobExcution();
        excution.setStatus(BatchStatus.STARTED);
        excution.setStartTime(LocalDateTime.now());

        if(tasklet == null || jobExcution == null) {
            excution.setStatus(BatchStatus.FAILED);
            excution.setEndTime(LocalDateTime.now());
            return excution;
        }
        jobExcution.beforeJob(excution);

        try {
            tasklet.execute();
            excution.setStatus(BatchStatus.FINISHED);
        } catch (Exception e) {
            excution.setStatus(BatchStatus.FAILED);
        } finally {
        excution.setEndTime(LocalDateTime.now());
        jobExcution.beforeJob(excution);
        }

        return excution;
    }

}
