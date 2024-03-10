package com.test.springbootasssignment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.test.springbootasssignment.service.UserService;

@Configuration

@EnableWebSecurity

public class SecurityConfigure<JwtRequestFilter>  extends WebSecurityConfiguration {



		    @Autowired

		    private UserDetailsService userDetailsService;



		    @Autowired

		    private JwtRequestFilter jwtRequestFilter;



		    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

		    }



		    @Bean

		    public PasswordEncoder passwordEncoder() {

		        return new BCryptPasswordEncoder();

		    }



		    protected void configure(HttpSecurity http) throws Exception {

		        http.csrf().disable()

		            .authorizeRequests()

		                .requestMatchers("/api/authenticate").permitAll() // Endpoint for authentication

		                .requestMatchers("/api/roles/**").hasRole("ADMIN")

		                .requestMatchers("/api/users/**").hasRole("ADMIN")

		                .requestMatchers("/api/employees/**").hasRole("ADMIN")

		                .anyRequest().authenticated()

		            .and()

		            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



		        // Add JWT filter

		        http.addFilterBefore((Filter) jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		    }



		    @Bean

		    public JwtUtil jwtUtil() {

		        return new JwtUtil();

		    }

		}