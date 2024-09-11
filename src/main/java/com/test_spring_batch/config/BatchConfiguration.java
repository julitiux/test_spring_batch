package com.test_spring_batch.config;

import com.test_spring_batch.steps.ItemProcessorStep;
import com.test_spring_batch.steps.ItemReaderStep;
import com.test_spring_batch.steps.ItemWritterStep;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Bean
  @JobScope
  public ItemReaderStep itemReaderStep() {
    return new ItemReaderStep();
  }

  @Bean
  @JobScope
  public ItemProcessorStep itemProcessorStep() {
    return new ItemProcessorStep();
  }

  @Bean
  @JobScope
  public ItemWritterStep itemWritterStep() {
    return new ItemWritterStep();
  }

  @Bean
  public Step readFileStep() {
    return stepBuilderFactory
      .get("itemReaderStep")
      .tasklet(itemReaderStep())
      .build();
  }

}
