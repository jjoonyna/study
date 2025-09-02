package com.example.batchstudy_1.batch;

public interface ItemProcessor<I, O> {

    O process(I item);
}
