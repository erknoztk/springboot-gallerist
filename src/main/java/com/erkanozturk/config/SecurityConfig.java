package com.erkanozturk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.erkanozturk.handler.AuthEntryPoint;
import com.erkanozturk.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

          public static final String AUTHENTICATE = "/authenticate"; // izinli
	public static final String REGISTER = "/register";
	public static final String REFRESH_TOKEN = "/refreshToken";

          @Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private AuthEntryPoint authEntryPoint;


          @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    		
		http
        		.csrf(csrf -> csrf.disable())

        		.sessionManagement(session ->
            		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		)

        		.authenticationProvider(authenticationProvider)

        		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

        		.authorizeHttpRequests(request -> request // bu istekler controller direk gecer
            		.requestMatchers(AUTHENTICATE, REGISTER, REFRESH_TOKEN)
			.permitAll() // kalanlar tokenlar ile girer
            		.anyRequest()
			.authenticated()
        		)
		
		

        		.exceptionHandling(exception ->
            		exception.authenticationEntryPoint(authEntryPoint)
        		);

    return http.build();
}

}
