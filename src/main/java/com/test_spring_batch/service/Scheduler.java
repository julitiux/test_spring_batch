package com.test_spring_batch.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

  @Scheduled(fixedRate = 1000)
  public void fixedRateScheduler() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date now = new Date();
    String stringDate = sdf.format(now);
    System.out.println("Fixed Rate Scheduler %s%n".formatted(stringDate));
  }

}
