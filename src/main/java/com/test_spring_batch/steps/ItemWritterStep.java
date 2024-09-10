package com.test_spring_batch.steps;

import com.test_spring_batch.domain.AfOds;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;
import java.util.Objects;

public class ItemWritterStep implements Tasklet {
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

    List<AfOds> afOdsList = (List<AfOds>) chunkContext
                                                  .getStepContext()
                                                  .getStepExecution()
                                                  .getJobExecution()
                                                  .getExecutionContext()
                                                  .get("afOdsList");

    afOdsList.forEach( afOds -> {
      if(!Objects.isNull(afOds))
        System.out.println(afOds.toString());
    });

    // TODO: dont forget implement the Repo

    return null;
  }
}
