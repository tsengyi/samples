package com.toncent.security.config;

import com.toncent.security.domain.Resource;
import com.toncent.security.domain.Role;
import com.toncent.security.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Iterator;
import java.util.Set;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/4
 * TIME  : 10:48
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry =
                http.authorizeRequests().antMatchers("/").permitAll();

        Iterable<Role> roles = applicationUserDetailsService.listRoles();
        for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext(); ) {
            Role role = iterator.next();

            Set<Resource> roleResources = role.getResources();

            for (Iterator<Resource> resourceIterator = roleResources.iterator(); resourceIterator.hasNext(); ) {
                Resource resource = resourceIterator.next();

                urlRegistry.antMatchers(resource.getUrl()).hasAnyAuthority(role.getAuthority());
            }
            urlRegistry.anyRequest().fullyAuthenticated();
        }
        urlRegistry
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
