package com.shoon.batch.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatchScheduler {
    private final Job job;
    private final JobLauncher jobLauncher;

    // 5초
    @Scheduled(fixedDelay = 5 * 1000L)
    public void executeJob(){
        try{
            jobLauncher.run(
                    job,
                    new JobParametersBuilder()
                        .addString("datetime", LocalDateTime.now().toString())
                    .toJobParameters()
            );
        } catch (JobExecutionException e) {
            log.debug("Exception : {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
