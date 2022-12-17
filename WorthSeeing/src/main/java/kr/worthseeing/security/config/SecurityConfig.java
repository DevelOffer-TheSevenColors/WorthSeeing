package kr.worthseeing.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.worthseeing.security.service.impl.BoardUserDetailServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private BoardUserDetailServiceImpl boardUserDetailService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests().antMatchers("/system/**").permitAll();
		security.authorizeHttpRequests().antMatchers("/member/**","/board/**","/mail").authenticated();
		security.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
		
		security.csrf().disable();
		
		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/main",true);
		
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/system/login");

		security.userDetailsService(boardUserDetailService);
	}
	
}
