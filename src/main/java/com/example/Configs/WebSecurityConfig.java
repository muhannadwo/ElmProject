package com.example.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                    .usersByUsernameQuery("select firstname, password, deleted "
                            + "from users where firstname=?")
                    .authoritiesByUsernameQuery("select firstname, role "
                            + "from users where firstname=?")
                    .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .authorizeRequests()
//                    .antMatchers(HttpMethod.GET,"/api/users/all/users").hasRole("ADMIN")
                    .antMatchers("/login").permitAll()
                    .antMatchers("/api/users/create/**").permitAll()
                    .antMatchers("/api/events/approved").permitAll()
                    .anyRequest().authenticated()
                    .and().httpBasic(); // Authenticate users with HTTP basic authentication


        }
}
