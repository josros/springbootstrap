package de.jro.demo.serviceone.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSources {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource primaryDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @Primary
  @Profile(value = "dev")
  @ConfigurationProperties(prefix = "spring.datasource2")
  public DataSource secondaryDataSource() {
    return DataSourceBuilder.create().build();
  }

}
