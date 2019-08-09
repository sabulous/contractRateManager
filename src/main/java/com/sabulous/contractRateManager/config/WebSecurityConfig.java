package com.sabulous.contractRateManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        // .antMatchers("/admin/**").hasRole("ADMIN")
        // .antMatchers("/anonymous*").anonymous()
        .antMatchers("/login*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        // .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/contracts", true)
        //.failureUrl("/login.html?error=true")
        // .failureHandler(authenticationFailureHandler())
        .and()
        .logout()
        // .logoutUrl("/perform_logout")
        .deleteCookies("JSESSIONID")
        // .logoutSuccessHandler(logoutSuccessHandler())
        ;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("sab")
                .password("sabulous")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}