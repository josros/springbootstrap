package de.jro.demo.web.defaults.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class CorsConfig {

  @Value("${cors-allowedorigins}")
  String allowedOriginsFromConfig;

  @Bean
  @Order(-1000)
  public SimpleCorsFilter corsFilter() {
    return new SimpleCorsFilter(allowedOriginsFromConfig);
  }

}
