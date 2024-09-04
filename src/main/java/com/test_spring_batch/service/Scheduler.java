package com.test_spring_batch.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

  @Scheduled(fixedDelay = 3000)
  public void fixedDelayScheduler() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date now = new Date();
    String stringDate = sdf.format(now);
    System.out.println("Fixed Delay Scheduler %s%n".formatted(stringDate));
  }


}
