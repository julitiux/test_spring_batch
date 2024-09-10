package com.test_spring_batch.config;

import com.test_spring_batch.steps.ItemProcessorStep;
import com.test_spring_batch.steps.ItemReaderStep;
import com.test_spring_batch.steps.ItemWritterStep;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Bean
  public ItemReaderStep itemReaderStep() {
    return new ItemReaderStep();
  }

  @Bean
  public ItemProcessorStep itemProcessorStep() {
    return new ItemProcessorStep();
  }

  @Bean
  public ItemWritterStep itemWritterStep() {
    return new ItemWritterStep();
  }

}
