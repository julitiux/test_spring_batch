package com.test_spring_batch.steps;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.FileReader;
import java.io.Reader;

@Slf4j
public class ItemReaderStep implements Tasklet {

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

    Reader reader = new FileReader(resourceLoader.getResource("classpath:emailBlackList.csv").getFile());

    CSVParser parser = new CSVParserBuilder()
      .withSeparator('|')
      .build();

    CSVReader csvReader = new CSVReaderBuilder(reader)
      .withCSVParser(parser)
      .build();

    return null;
  }
}
