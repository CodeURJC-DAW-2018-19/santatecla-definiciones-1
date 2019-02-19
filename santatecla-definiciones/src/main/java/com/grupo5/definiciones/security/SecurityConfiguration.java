package com.grupo5.definiciones.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
		
		//Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loadChapters").permitAll();
		http.authorizeRequests().antMatchers("/assets/**").permitAll();
		
		//Private pages
		http.authorizeRequests().antMatchers("/concept/**").hasAnyRole("ESTUDIANTE","DOCENTE");
		http.authorizeRequests().antMatchers("/mark/**").hasAnyRole("DOCENTE");
		http.authorizeRequests().antMatchers("/delete/**").hasAnyRole("DOCENTE");
		
		//Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		//http.formLogin().defaultSuccessUrl("/concepts");
		http.formLogin().failureUrl("/");
		
		//Logout
		http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
		
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider);
		
	}
	

}

