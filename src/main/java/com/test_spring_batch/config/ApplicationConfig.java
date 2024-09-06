package com.test_spring_batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    return dataSource;
  }

}
