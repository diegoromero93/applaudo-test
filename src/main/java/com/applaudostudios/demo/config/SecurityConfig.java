package com.applaudostudios.demo.config;

import com.applaudostudios.demo.config.security.OauthJwtEntryPoint;
import com.applaudostudios.demo.config.security.OauthJwtFilter;
import com.applaudostudios.demo.services.impl.OauthJwtUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @NonNull
    private final DataSource dataSource;

    @NonNull
    private final OauthJwtEntryPoint authorizedEntryPoint;

    @NonNull
    private final OauthJwtUserService oauthJwtUserService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(oauthJwtUserService).passwordEncoder(passwordEncoder());
    }

    // Secure the endpoins with HTTP Basic authentication
    //You need to secure the following provided REST endpoints:
    //POST request to /app/item , DELETE request to /app/item/{itemId} , DELETE request to /app/item
    //this operation can only be authorized to a user having the role of ​ADMIN​.
    // POST request to /app/item -> this operation can be authorized to both ADMIN and USER roles
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth").permitAll()
                .antMatchers(HttpMethod.DELETE, "/app/item").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/app/item/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/app/item").hasAnyRole("ADMIN",  "USER")
                .antMatchers(HttpMethod.PUT, "/app/item").hasAnyRole("ADMIN",  "USER")
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authorizedEntryPoint)
                .and()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(oauthJwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OauthJwtFilter oauthJwtFilter() {
        return new OauthJwtFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
