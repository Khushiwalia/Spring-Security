package com.id.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.id.springsecurity.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
                //.csrf().disable()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
				.antMatchers("/sign").permitAll().antMatchers("/public/**").hasRole("NORMAL")

				.antMatchers("/users/**").hasRole("ADMIN").anyRequest().authenticated().and()
				.formLogin().loginPage("/sign")
				.loginProcessingUrl("/doLogin")
				.defaultSuccessUrl("/users/");
				//.httpBasic();

	}

	private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl antMatchers(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
		
		//auth.inMemoryAuthentication().withUser("Kavya").password("verma").roles("NORMAL");
		//auth.inMemoryAuthentication().withUser("Amita").password("Rawat").roles("ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		 return new BCryptPasswordEncoder();
	}

}
