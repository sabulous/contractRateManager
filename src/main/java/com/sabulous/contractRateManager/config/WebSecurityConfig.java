package com.sabulous.contractRateManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/hello", "h2-console/**").permitAll()
                // DO NOT allow anything else
                // any request besides above line (ones in the antMatchers) require
                // authentication
                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
                .permitAll();

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // required to enable accessing h2-console
        http.headers().frameOptions().disable();

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // String encoded = new BCryptPasswordEncoder().encode("password");
        // System.out.println("Encoded password is:" + encoded);

        UserDetails user = User.withDefaultPasswordEncoder()
                            .username("user")                            
                            .password("password")
                            .roles("USER")
                            .build();


        return new InMemoryUserDetailsManager(user);
    }

    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    // //DaoAuthenticationProvider aProvider = new DaoAuthenticationProvider();
    // return super.authenticationManagerBean();
    // }

    // @Bean
    // public PasswordEncoder bcryptPasswordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     super.configure(auth);
    // }
}   