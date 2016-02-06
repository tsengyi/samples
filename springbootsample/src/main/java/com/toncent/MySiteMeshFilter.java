package com.toncent;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/6
 * TIME  : 12:23
 */
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/layout/decorator.jsp");
    }
}
