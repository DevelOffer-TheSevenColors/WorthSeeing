package kr.worthseeing.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.worthseeing.security.service.impl.UserDetailServiceImpl; 
 
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationFailureHandler customFailureHandler;

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	/* static 관련설정은 무시 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests().antMatchers("/system/**", "/error/**").permitAll();
		security.authorizeHttpRequests().antMatchers("/member/**", "/mail","/reply/**", "/notify/**").authenticated();
		security.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");

		security.csrf().disable(); 

		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/main", true);

		security.exceptionHandling().accessDeniedPage("/system/accessDenied");

		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/system/login");
 
		security.userDetailsService(userDetailService);
		
		security.formLogin().failureHandler(customFailureHandler);
	}

}


