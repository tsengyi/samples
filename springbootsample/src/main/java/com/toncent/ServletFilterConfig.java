package com.toncent;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/6
 * TIME  : 12:20
 */
@Configurable
public class ServletFilterConfig {

    @Bean
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MySiteMeshFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
