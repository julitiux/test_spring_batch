package com.test_spring_batch.config;

import com.mongodb.client.MongoClients;
import com.test_spring_batch.domain.AfOdsMongo;
import com.test_spring_batch.processor.AfOdsProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AfOdsConfiguration {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  @Value("${spring.data.mongodb.uri}")
  private String mongoUri;

  @Value("${spring.data.mongodb.database}")
  private String mongoDatabase;

  @Bean
  public MongoTemplate mongoTemplate() {
    // Crear una conexión con MongoDB usando el URI y pasarla a MongoTemplate
    return new MongoTemplate(MongoClients.create(mongoUri), mongoDatabase);
  }

  @Bean
  public FlatFileItemReader<AfOdsMongo> itemReaderMongo() {
    FlatFileItemReader<AfOdsMongo> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(new FileSystemResource("src/main/resources/emailBlackList.csv"));
    itemReader.setName("csvReaderForMongo");
    itemReader.setLinesToSkip(1);
    itemReader.setLineMapper(lineMapper());
    return itemReader;
  }

  @Bean
  public AfOdsProcessor processor() {
    return new AfOdsProcessor();
  }

  @Bean
  public MongoItemWriter<AfOdsMongo> writerMongo() {
    MongoItemWriter<AfOdsMongo> writer = new MongoItemWriter<>();
    writer.setTemplate(mongoTemplate());
    writer.setCollection("AfOdsMongo");
    return writer;
  }

  @Bean
  public Step stepMongo() {
    return new StepBuilder("csvMongo", jobRepository)
      .<AfOdsMongo, AfOdsMongo>chunk(10, platformTransactionManager)
      .reader(itemReaderMongo())
      .processor(processor())
      .writer(writerMongo())
      .build();
  }

  @Bean
  public Job runJobMongo() {
    return new JobBuilder("importAfOdsMongo", jobRepository)
      .start(stepMongo())
      .build();
  }

  private LineMapper<AfOdsMongo> lineMapper() {
    DefaultLineMapper<AfOdsMongo> lineMapper = new DefaultLineMapper<>();

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter("|");
    lineTokenizer.setStrict(false);
    lineTokenizer.setNames("codeEntity", "idListBlack", "codeListBlack");

    BeanWrapperFieldSetMapper<AfOdsMongo> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(AfOdsMongo.class);

    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);

    return lineMapper;
  }

}
