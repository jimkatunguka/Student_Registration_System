package com.group3.courseenrollment.security;

import com.group3.courseenrollment.configuration.ApplicationProperties;
import com.group3.courseenrollment.service.impl.CredentialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CredentialServiceImpl credentialService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
     BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(credentialService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http.cors()
                        .and()
                        .csrf().disable()
                        .authorizeRequests()
                        .antMatchers("/blocks/**",
                                "/courses/**","/enrollments/**","/offerings/**","/students/**").authenticated()
                        .antMatchers("/").permitAll()
                        .and()
                        .addFilter(new JWTAuthenticationFilter(authenticationManager(),applicationProperties))
                        .addFilter(new JWTAuthorizationFilter(authenticationManager(),applicationProperties))
                // this disables session creation on spring security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }




}
