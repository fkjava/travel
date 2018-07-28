package org.fkjava.travel.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("org.fkjava")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "org.fkjava")
public class CoreConfig {

}
