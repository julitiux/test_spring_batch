package com.test_spring_batch.config;

import com.test_spring_batch.domain.AfOdsMongo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class MongoConfiguration {

  @Bean
  public FlatFileItemReader<AfOdsMongo> itemReader() {
    FlatFileItemReader<AfOdsMongo> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(new FileSystemResource("src/main/resources/emailBlackList.csv"));
    itemReader.setName("csvReaderForMongo");
    itemReader.setLinesToSkip(1);
    itemReader.setLineMapper(lineMapper());
    return itemReader;
  }

}
