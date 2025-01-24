package com.ApplicationStockMarket.Configuration;



import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

//import com.Journal.Application.Service.UserDetailsServiceImpl;

//import com.Journal.Application.Service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity

public class SpringSecurity {

	
	    //@Autowired
	    //private UserDetailsServiceImpl userDetailsService;

	   /* @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	       // http.authenticationManager(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));
	        
	        return http.authorizeHttpRequests(request -> request
	                .requestMatchers("/public/**", "/student/**").permitAll()
	                .requestMatchers("/journal/**").authenticated()
	                .anyRequest().authenticated())
	            .httpBasic(Customizer.withDefaults())
	            .csrf(AbstractHttpConfigurer::disable)
	            .build();
	        
	        
	        
	    }
	    */
	
	
	 @Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired 
		private JwtFilter jwtFilter;
		@Bean
		public AuthenticationProvider authProvider() {
			DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
			provider.setUserDetailsService(userDetailsService);
			provider.setPasswordEncoder(passwordEncoder());
			return provider;
		}
		  @Bean
		    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		        return config.getAuthenticationManager();

		    }

		 @Bean
		    public PasswordEncoder passwordEncoder() {
		        return new BCryptPasswordEncoder();
		    }
		
	    
	    @Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

			http.csrf(customizer -> customizer.disable())
					.authorizeHttpRequests(request -> request
							.requestMatchers("/public/**", "/student/**").permitAll()
			                .requestMatchers("/journal/**").authenticated()
			                .requestMatchers("/admin/**").hasAuthority("Admin")
							.anyRequest().authenticated())
					//.anyRequest().permitAll())
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

			return http.build();
		}
/*.hasAnyRole("Admin")*/


	   
		
		
	}

    

