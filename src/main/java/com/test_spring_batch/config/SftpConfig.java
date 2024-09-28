package com.test_spring_batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

}
