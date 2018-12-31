package com.example.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


        @Autowired
        private DataSource dataSource;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery("select username, password, deleted "
                            + "from users where username=?")
                    .authoritiesByUsernameQuery("select username, role "
                            + "from users where username=?")
                    .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .httpBasic().and().authorizeRequests()
//                    .anyRequest().permitAll()
//                    .antMatchers(HttpMethod.GET,"/api/users/all/users").permitAll()
//                    .antMatchers("/api/users/update/**").permitAll()
//                    .antMatchers("/api/users/user/**").permitAll()
//                    .antMatchers("/api/users/delete/**").permitAll()
//                    .antMatchers("/login").permitAll()
                      .antMatchers("/api/users/create/**").permitAll()
//                    .antMatchers("/api/events/all/events").permitAll()
//                    .antMatchers("/api/events/event/**").permitAll()
//                    .antMatchers("/api/events/delete/**").permitAll()
//                    .antMatchers("/api/events/active/**").permitAll()
                    .anyRequest().authenticated()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// Authenticate users with HTTP basic authentication


        }
}
