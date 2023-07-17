package com.sapient.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Value("${eureka.username:anas}")
	String eurekaUsername;
	@Value("${eureka.password:root}")
	String eurekaPassword;

	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		UserDetails userDetails = User.withDefaultPasswordEncoder().username(eurekaUsername).password(eurekaPassword)
				.roles("USER").build();
		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		return httpSecurity.build();
	}
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.csrf().ignoringRequestMatchers("/eureka/**");
//		return httpSecurity.build();
//	}

}
