package com.lawencon.laundry.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author WILLIAM
 *
 */
@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ApiSecurityServiceImpl apiSecurityImpl;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().authorizeRequests()
		.anyRequest().authenticated();
		http.addFilter(new AuthenticationFilter(super.authenticationManager()));
		http.addFilter(new AuthorizationFilter(super.authenticationManager()));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth){
		try {
			auth.userDetailsService(apiSecurityImpl).passwordEncoder(bCryptPasswordEncode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(HttpMethod.POST, "/user");
	}
}
