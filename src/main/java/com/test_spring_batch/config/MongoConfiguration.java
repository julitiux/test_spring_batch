package com.test_spring_batch.config;

import com.test_spring_batch.domain.AfOdsMongo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
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

  @Bean
  public AfOdsMongoProcessor processor(){
    return new AfOdsMongoProcessor();
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
