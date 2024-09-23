package com.test_spring_batch.processor;

import com.test_spring_batch.domain.AfOds;
import org.springframework.batch.item.ItemProcessor;

public class AfOdsProcessor implements ItemProcessor<AfOds, AfOds> {

  @Override
  public AfOds process(AfOds afOdsMongo) throws Exception {
    return afOdsMongo;
  }

}
