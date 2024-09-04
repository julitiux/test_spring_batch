package com.test_spring_batch.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

  @Scheduled(cron = "0 * 19 * * ?")
  public void cronJobScheduler() {

  }


}
