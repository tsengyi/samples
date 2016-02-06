package com.toncent.security.config;

import com.toncent.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/4
 * TIME  : 10:48
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityService securityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry =
                http.authorizeRequests().antMatchers("/").permitAll();

//        Iterable<Role> roles = securityService.listRoles();
//        for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext(); ) {
//            Role role = iterator.next();
//
//            Set<Resource> roleResources = role.getResources();
//
//            for (Iterator<Resource> resourceIterator = roleResources.iterator(); resourceIterator.hasNext(); ) {
//                Resource resource = resourceIterator.next();
//
//                urlRegistry.antMatchers(resource.getUrl()).hasAnyAuthority(role.getAuthority());
//            }
//            urlRegistry.anyRequest().fullyAuthenticated();
//        }
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
        auth.userDetailsService(securityService).passwordEncoder(new PlaintextPasswordEncoder());
    }


}
