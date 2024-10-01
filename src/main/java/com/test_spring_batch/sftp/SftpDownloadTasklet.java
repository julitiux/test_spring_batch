package com.test_spring_batch.sftp;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SftpDownloadTasklet implements Tasklet {

  @Autowired
  SftpInboundFileSynchronizingMessageSource sftpMessageSource;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    // Aquí puedes ejecutar la lógica para sincronizar archivos desde el SFTP
    Message<?> message;
    while ((message = sftpMessageSource.receive()) != null) {
      System.out.println("Archivo descargado: " + message.getPayload());
    }
    return RepeatStatus.FINISHED;
  }

}
