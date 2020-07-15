package com.joshua.auth;

import com.joshua.findcomputer.findcomp_impl.domain.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder passwordEncoder;
	private final UserServiceImpl userService;

	@Autowired
	public SecurityConfig(PasswordEncoder passwordEncoder, UserServiceImpl userService) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}

	@Bean
	public DaoAuthenticationProvider findcompDaoAuthenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}

}
