package com.toncent.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/15
 * TIME  : 14:44
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                String requestURI = request.getRequestURI();
                String decorator = "layout/webDecorator";
                if (StringUtils.startsWithIgnoreCase(requestURI, "/wx")) {
                    decorator = "layout/wapDecorator";
                }
                if (modelAndView != null) {
                    ModelMap modelMap = modelAndView.getModelMap();
                    if (modelMap != null) {
                        modelMap.addAttribute("decorator", decorator);
                    }
                }
            }
        });
    }
}