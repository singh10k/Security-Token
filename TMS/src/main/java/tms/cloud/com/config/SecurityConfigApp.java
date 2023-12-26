package tms.cloud.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigApp {

	@Autowired
	private UserDetailsService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//authentication 
		auth.userDetailsService(service).passwordEncoder(encoder);
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		//authorization
		http.authorizeHttpRequests().antMatchers("/loginPage","/register", "/home").permitAll()
		.antMatchers("/**/*.js", "/**/*.css", "/**/*.png").permitAll()
//		.antMatchers("/bank/offers", "/TMS/home").authenticated()
		.antMatchers("/bank/balance").hasAnyAuthority("CUSTOMER","MANAGER")
		.antMatchers("/bank/loanApprove").hasAuthority("MANAGER")
		.anyRequest().authenticated()
		.and().formLogin()
		.defaultSuccessUrl("/TMS/home",true)//home page url
		.loginPage("/loginPage") //for GET mode request to launch form page
		.loginProcessingUrl("/login") //for POST mode request to submit and process the page
		.failureUrl("/loginPage/?error")//Authentication failed url
		.and().rememberMe()
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		.logoutSuccessUrl("/home/?logout") //after logout url
		
		.and().exceptionHandling().accessDeniedPage("/denied")
		.and().sessionManagement().maximumSessions(5).maxSessionsPreventsLogin(true);

		return http.build();
	}
}

