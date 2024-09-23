package com.test_spring_batch.config;

import com.test_spring_batch.domain.AfOdsMongo;
import org.springframework.batch.item.ItemProcessor;

public class AfOdsProcessor implements ItemProcessor<AfOdsMongo, AfOdsMongo> {

  @Override
  public AfOdsMongo process(AfOdsMongo afOdsMongo) throws Exception {
    return afOdsMongo;
  }

}
