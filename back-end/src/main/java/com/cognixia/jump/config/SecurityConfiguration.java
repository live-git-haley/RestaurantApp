package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

   @Autowired
	UserDetailsService userDetailsService;
	
	// specify the users for authentication
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		
		// for in memory
//		auth.inMemoryAuthentication()
//			.withUser("user1")
//			.password("123")
//			.roles("USER")
//			.and()
//			.withUser("admin1")
//			.password("123")
//			.roles("ADMIN");
		
		// using users from a database
		auth.userDetailsService(userDetailsService);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}
	
	// specify which users can access which apis
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		
		// VERSION 1: Allow all requests for anyone (no security, can uncomment this for testing)
//		http.csrf().disable() // disable security for CSRF so we can declare access for PUT requests
//			.authorizeRequests()
//			.antMatchers("/**").permitAll()
//			.and().httpBasic();
		
/*******************************************/
		
		// VERSION 2: Contains security restrictions
		// go from most restrictive to least restrictive
		// this code restricts getting/adding/deleting users and adding/updating/deleting restaurants to an admin
		// adding/updating/deleting reviews and photos restricted to a user
		http.csrf().disable() 
		.authorizeRequests()
		.antMatchers("/api/users").hasRole("ADMIN") //these are still accessible if something is put at end of url
		.antMatchers("/api/user/add").hasRole("ADMIN") 
		.antMatchers("/api/user/delete/{id}").hasRole("ADMIN") 
		.antMatchers( HttpMethod.POST, "/api/add/restaurant" ).hasRole("ADMIN")
		.antMatchers( HttpMethod.PUT, "/api/update/restaurant/" ).hasRole("ADMIN")
		.antMatchers( HttpMethod.DELETE, "/api/delete/restaurant/{id}" ).hasRole("ADMIN")
		.antMatchers( HttpMethod.POST, "/api/add/review" ).hasRole("USER")
		.antMatchers( HttpMethod.PUT, "/api/update/review" ).hasRole("USER")
		.antMatchers( HttpMethod.DELETE, "/api/delete/review/{id}" ).hasRole("USER")
		.antMatchers( HttpMethod.POST, "/api/add/photo" ).hasRole("USER")
		.antMatchers( HttpMethod.PUT, "/api/update/photo" ).hasRole("USER")
		.antMatchers( HttpMethod.DELETE, "/api/delete/photo/{id}" ).hasRole("USER")
		.and().httpBasic();
		
		
	}
}
