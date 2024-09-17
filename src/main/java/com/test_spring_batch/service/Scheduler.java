package com.test_spring_batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  @Scheduled(fixedRate = 10000)
  public void fixedRateScheduler() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date now = new Date();
    String stringDate = sdf.format(now);
    System.out.println("Starting %s%n".formatted(stringDate));

    JobParameters jobParameters = new JobParametersBuilder()
      .addLong("startAt", System.currentTimeMillis())
      .toJobParameters();

    try {
      jobLauncher.run(job, jobParameters);
    } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
             JobParametersInvalidException e) {
      throw new RuntimeException(e);
    }

    now = new Date();
    stringDate = sdf.format(now);
    System.out.println("Ending %s%n".formatted(stringDate));

  }

}
