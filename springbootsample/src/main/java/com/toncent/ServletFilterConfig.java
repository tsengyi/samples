package com.toncent;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/6
 * TIME  : 12:20
 */
@Configuration
public class ServletFilterConfig {
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
