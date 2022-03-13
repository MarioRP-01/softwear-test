package com.softwear.webapp5.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

       
        // Private pages
        http.authorizeRequests().antMatchers("/cart*").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/purchaseHistory*").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/wishlist*").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/loginSuccess").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/userProfile").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/userProfile/*").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/admin/*").hasAnyRole("ADMIN");

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("usernameLogin");
        http.formLogin().passwordParameter("passwordLogin");
        http.formLogin().defaultSuccessUrl("/loginSuccess");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }
}
