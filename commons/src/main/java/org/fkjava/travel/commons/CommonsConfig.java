package org.fkjava.travel.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("org.fkjava")
@EnableAutoConfiguration()
@Configuration()
@EnableWebMvc()
@EnableJpaRepositories()
public class CommonsConfig {
}
