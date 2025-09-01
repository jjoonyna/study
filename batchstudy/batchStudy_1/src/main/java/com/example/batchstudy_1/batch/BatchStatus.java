package com.example.batchstudy_1.batch;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BatchStatus {
    STARTED,
    FAILED,
    FINISHED
}
