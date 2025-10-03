package com.example.batchstudy_1.batch;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class StepJob extends AbstractJob {

    private final List<Step> steps;

    public StepJob(List<Step> steps, JobExecutionListener jobExecutionListener) {
        super(jobExecutionListener);
        this.steps = steps;
    }

    @Override
    public void doExecute() {
        steps.forEach(Step::execute);
    }
}
