package de.jro.demo.serviceone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.jro.demo.web.defaults.EnableWebDefault;

@SpringBootApplication
@EnableWebDefault
public class ServiceApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(ServiceApplication.class, args);
  }
  
}
