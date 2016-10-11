package de.jro.demo.serviceone.test.hello.data;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"de.jro.demo.serviceone.hello.persistence.data"})
@EnableJpaRepositories(basePackages = {"de.jro.demo.serviceone.hello.persistence"})
@EnableTransactionManagement
public class HelloRepositoryTestConfig {

}
