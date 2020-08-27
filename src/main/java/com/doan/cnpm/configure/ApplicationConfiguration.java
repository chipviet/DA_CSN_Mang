package com.doan.cnpm.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.doan.cnpm.repositories")
public class ApplicationConfiguration {



}
