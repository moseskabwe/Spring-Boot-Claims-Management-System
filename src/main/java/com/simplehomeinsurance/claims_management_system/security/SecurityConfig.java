package com.simplehomeinsurance.claims_management_system.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {	
	@Autowired
	private DataSource myDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(myDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/policyholders/showPolicyDetails").hasRole("CSR")
			.antMatchers("/dashboard/addClaimDetails").hasRole("CSR")
			.antMatchers("/dashboard/finaliseClaim").hasRole("ADJUSTER")
			.antMatchers("/dashboard/myClaims").hasRole("ADJUSTER")
			.antMatchers("/declineClaim").hasRole("ADJUSTER")
			.antMatchers("/resources/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and().logout().permitAll();
	}
}
