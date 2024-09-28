package com.test_spring_batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
public class SftpConfig {

  @Bean
  public DefaultSftpSessionFactory sftpSessionFactory() {
    DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
    factory.setHost("localhost");         // Cambia el host al de tu servidor SFTP
    factory.setPort(2222);                // Cambia el puerto si es necesario
    factory.setUser("user");
    factory.setPassword("password");
    factory.setAllowUnknownKeys(true);
    return factory;
  }

  @Bean
  public SftpInboundFileSynchronizer sftpInboundFileSynchronizer() {
    SftpInboundFileSynchronizer synchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
    synchronizer.setDeleteRemoteFiles(false);  // Si no quieres eliminar los archivos después de descargarlos
    synchronizer.setRemoteDirectory("/remote/path"); // Directorio en el servidor SFTP
    synchronizer.setFilter(new SftpSimplePatternFileListFilter("*.txt")); // Filtra archivos según su patrón
    return synchronizer;
  }

}
