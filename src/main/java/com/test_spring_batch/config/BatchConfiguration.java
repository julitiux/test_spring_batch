package com.test_spring_batch.config;

import com.test_spring_batch.domain.AfOds;
import com.test_spring_batch.repository.AfOdsRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  private AfOdsRepository afOdsRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private PlatformTransactionManager platformTransactionManager;


  @Bean
  public FlatFileItemReader<AfOds> itemReader() {
    FlatFileItemReader<AfOds> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(new FileSystemResource("src/main/resources/emailBlackList.csv"));
    itemReader.setName("csvReader");
    itemReader.setLinesToSkip(1);
    itemReader.setLineMapper(lineMapper());
    return itemReader;
  }

  @Bean
  public AfOdsProcessor processor() {
    return new AfOdsProcessor();
  }

  @Bean
  public RepositoryItemWriter<AfOds> writer() {
    RepositoryItemWriter<AfOds> writer = new RepositoryItemWriter<>();
    writer.setRepository(afOdsRepository);
    writer.setMethodName("save");
    return writer;
  }

  @Bean
  public Step importStep() {
    return new StepBuilder("csvImport", jobRepository)
      .<AfOds, AfOds>chunk(10, platformTransactionManager)
      .reader(itemReader())
      .processor(processor())
      .writer(writer())
      .build();
  }

  @Bean
  public Job runJob() {
    return new JobBuilder("importAfOds", jobRepository)
      .start(importStep())
      .build();
  }

  private LineMapper<AfOds> lineMapper() {
    DefaultLineMapper<AfOds> lineMapper = new DefaultLineMapper<>();

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter("|");
    lineTokenizer.setStrict(false);

    BeanWrapperFieldSetMapper<AfOds> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(AfOds.class);

    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);

    return lineMapper;
  }

}
