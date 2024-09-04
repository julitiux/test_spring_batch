package com.test_spring_batch.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

  @Scheduled(cron = "0 * 19 * * ?")
  public void cronJobScheduler() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date now = new Date();
    String stringDate = sdf.format(now);
    System.out.println("Java cron job expressions %s%n".formatted(stringDate));
  }


}
