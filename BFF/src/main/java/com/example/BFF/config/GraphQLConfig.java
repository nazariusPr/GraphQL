package com.example.BFF.config;

import static com.example.BFF.scalar.LocalDateTimeScalar.createLocalDateTimeScalar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

  @Bean
  public RuntimeWiringConfigurer runtimeWiringConfigurer() {
    return wiringBuilder -> wiringBuilder.scalar(createLocalDateTimeScalar());
  }
}
