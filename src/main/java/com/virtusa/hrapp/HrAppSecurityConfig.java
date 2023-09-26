package com.virtusa.hrapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.virtusa.hrapp.serviceImpl.AppHrDetailsService;

@Configuration
@EnableWebSecurity
public class HrAppSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Bean public static BCryptPasswordEncoder getBCryptPasswordEncoder() { return
	 * new BCryptPasswordEncoder(); }
	 */

	@Autowired
	@Qualifier("appUserDetailsService")   //NoBeanDefinitionException
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("appHrDetailsService")
	private UserDetailsService hrDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/login", "/", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
				.antMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
				.antMatchers("/registerAsHr","/getErrorPage", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
				.antMatchers("/postajob","/getdeleteajob","/deletejobbyid","/addJob","/viewapplicants").hasAuthority("HR")
				.antMatchers("/applyforajob","/applyjobbyid","/viewappliedjob","/withdrawApplicationbyid").hasAuthority("USER")
				.antMatchers("/hr/addNew").permitAll().antMatchers("/users/addNew").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/getDashboard")
				.and()
				.exceptionHandling().accessDeniedPage("/getErrorPage")
				.and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {

		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	 @Override
	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider authenticationProviderForUser = new DaoAuthenticationProvider();
		DaoAuthenticationProvider authenticationProviderForHr = new DaoAuthenticationProvider();

		authenticationProviderForUser.setUserDetailsService(userDetailsService);
		authenticationProviderForHr.setUserDetailsService(hrDetailsService);
		authenticationProviderForUser.setPasswordEncoder(getBCryptPasswordEncoder());
		authenticationProviderForHr.setPasswordEncoder(getBCryptPasswordEncoder());
		ProviderManager manager = new ProviderManager(authenticationProviderForUser, authenticationProviderForHr);
        return  manager;
		
	}

	/*
	 * @Configuration
	 * 
	 * @Order(1) public static class HrApp1Configuration extends
	 * WebSecurityConfigurerAdapter {
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("appUserDetailsService") private UserDetailsService
	 * userDetailsService;
	 * 
	 * public HrApp1Configuration() { super(); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.csrf().disable().authorizeRequests() .antMatchers("/login", "/",
	 * "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
	 * .antMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**",
	 * "/js/**").permitAll() .antMatchers("/registerAsHr", "/resources/**",
	 * "/css/**", "/fonts/**", "/img/**", "/js/**")
	 * .permitAll().antMatchers("/hr/addNew").permitAll().antMatchers(
	 * "/users/addNew").permitAll()
	 * .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll
	 * () .defaultSuccessUrl("/getDashboard").and().logout().invalidateHttpSession(
	 * true) .clearAuthentication(true).logoutRequestMatcher(new
	 * AntPathRequestMatcher("/logout")) .logoutSuccessUrl("/").permitAll();
	 * 
	 * }
	 * 
	 * 
	 * @Bean public BCryptPasswordEncoder getBCryptPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * 
	 * @Bean public AuthenticationProvider getAuthenticationProvider() {
	 * DaoAuthenticationProvider authenticationProvider = new
	 * DaoAuthenticationProvider();
	 * authenticationProvider.setUserDetailsService(userDetailsService);
	 * authenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
	 * 
	 * return authenticationProvider; }
	 * 
	 * }
	 * 
	 * @Configuration
	 * 
	 * @Order(2) public static class HrApp2Configuration extends
	 * WebSecurityConfigurerAdapter {
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("appHrDetailsService") private UserDetailsService
	 * hrDetailsService;
	 * 
	 * public HrApp2Configuration() { super(); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.csrf().disable().authorizeRequests() .antMatchers("/login", "/",
	 * "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
	 * .antMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**",
	 * "/js/**").permitAll() .antMatchers("/registerAsHr", "/resources/**",
	 * "/css/**", "/fonts/**", "/img/**", "/js/**")
	 * .permitAll().antMatchers("/hr/addNew").permitAll().antMatchers(
	 * "/users/addNew").permitAll()
	 * .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll
	 * () .defaultSuccessUrl("/getDashboard").and().logout().invalidateHttpSession(
	 * true) .clearAuthentication(true).logoutRequestMatcher(new
	 * AntPathRequestMatcher("/logout")) .logoutSuccessUrl("/").permitAll();
	 * 
	 * }
	 * 
	 * 
	 * @Bean public BCryptPasswordEncoder getBCryptPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * 
	 * @Bean public AuthenticationProvider getAuthenticationProvider() {
	 * DaoAuthenticationProvider authenticationProvider = new
	 * DaoAuthenticationProvider();
	 * authenticationProvider.setUserDetailsService(hrDetailsService);
	 * authenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
	 * 
	 * return authenticationProvider; }
	 * 
	 * }
	 */

}
