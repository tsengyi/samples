package com.toncent;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/6
 * TIME  : 12:20
 */
@Configuration
public class ServletFilterConfig {

    @Bean
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MySiteMeshFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
