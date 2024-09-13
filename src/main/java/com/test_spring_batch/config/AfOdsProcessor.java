package com.test_spring_batch.config;

import com.test_spring_batch.domain.AfOds;
import org.springframework.batch.item.ItemProcessor;

public class AfOdsProcessor implements ItemProcessor<AfOds, AfOds> {

  @Override
  public AfOds process(AfOds item) throws Exception {
    return null;
  }

}
