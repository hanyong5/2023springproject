package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {

	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
        	.cors((cors) -> cors.disable())
            .authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/guest/**").permitAll()
                .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
            );
        
        http.formLogin((formLogin) -> formLogin
			.loginPage("/loginForm") 			// default : /login
			.loginProcessingUrl("/j_spring_security_check")
//			.failureUrl("/loginForm?error") 	// default : /login?error
			.failureHandler(authenticationFailureHandler) 	// 
			
			.usernameParameter("j_username")	// default : j_username
			.passwordParameter("j_password") 	// default : j_password
			.permitAll());

        http.logout((logout) -> logout
        	.logoutUrl("/logout") // default
        	.logoutSuccessUrl("/")
        	.permitAll());
        
        return http.build();
    }
    
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user = User.builder()
//				.username("user")
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER")   // ROLE_USER 에서 ROLE_는 자동으로 붙는다.
//				.build();
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER", "ADMIN")
//				.build();
//
//		// 메모리에 사용자 정보를 담는다.
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
    // passwordEncoder()
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select name as userName, password, enabled"
					+ " from user_list where name=?")
			.authoritiesByUsernameQuery("select name as userName,authority"
					+ " from user_list where name=?")
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}








