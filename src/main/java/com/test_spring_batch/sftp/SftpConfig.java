package com.test_spring_batch.sftp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

import java.io.File;

@Configuration
public class SftpConfig {

  @Bean
  public DefaultSftpSessionFactory sftpSessionFactory() {
    DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
    factory.setHost("localhost");         // Cambia el host al de tu servidor SFTP
    factory.setPort(22);                // Cambia el puerto si es necesario
    factory.setUser("user");
    factory.setPassword("password");
    factory.setAllowUnknownKeys(true);
    return factory;
  }

  @Bean
  public SftpInboundFileSynchronizer sftpInboundFileSynchronizer() {
    SftpInboundFileSynchronizer synchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
    synchronizer.setDeleteRemoteFiles(false);  // Si no quieres eliminar los archivos después de descargarlos
    synchronizer.setRemoteDirectory("/upload"); // Directorio en el servidor SFTP
    synchronizer.setFilter(new SftpSimplePatternFileListFilter("*.csv")); // Filtra archivos según su patrón
    return synchronizer;
  }

  @Bean
  public SftpInboundFileSynchronizingMessageSource sftpMessageSource() {
    SftpInboundFileSynchronizingMessageSource source = new SftpInboundFileSynchronizingMessageSource(sftpInboundFileSynchronizer());
    source.setLocalDirectory(new File("/Users/rrodriguez/git/github/test_spring_batch/src/main/resources/downloaded")); // Directorio donde se guardarán los archivos descargados
    source.setAutoCreateLocalDirectory(true);
    return source;
  }

}
