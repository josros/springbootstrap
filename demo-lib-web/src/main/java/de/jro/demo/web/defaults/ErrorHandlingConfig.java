package de.jro.demo.web.defaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlingConfig {

  @Bean
  public ErrorHandler exceptionHandler() {
    return new ErrorHandler();
  }
  
}
