package com.example.batchstudy_1.batch;


public interface JobExecutionListener {

    void beforeJob(JobExcution  excution);
    void afterJob(JobExcution  excution);
}
