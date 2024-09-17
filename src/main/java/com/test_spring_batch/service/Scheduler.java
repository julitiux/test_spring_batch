package com.test_spring_batch.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class Scheduler {

  @Scheduled(fixedRate = 1000)
  public void fixedRateScheduler() {

    System.out.println(" {} " + new Date());

  }

}
