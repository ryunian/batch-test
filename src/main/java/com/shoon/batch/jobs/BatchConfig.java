package com.shoon.batch.jobs;

import com.shoon.batch.taklets.BatchTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        // JobBuilderFactory 로 batchJob 생성
        return jobBuilderFactory.get("batchJob")
                .start(batchStep())
                .build();
    }

    @Bean
    public Step batchStep() {
        // StepBuilderFactory 로 batchStep 생성
        return stepBuilderFactory.get("batchStep")
                .tasklet(new BatchTasklet())
                .build();
    }
}
