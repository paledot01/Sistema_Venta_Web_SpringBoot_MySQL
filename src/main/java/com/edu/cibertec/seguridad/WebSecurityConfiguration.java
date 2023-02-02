package com.edu.cibertec.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	// @Bean es una anotacion a nivel de metodo que es analago del elemento XML <bean/>
	// 1. necesita un usuario y un codificador, la interfaz papa es PasswordEncoder.
	// 2. necesita un codificador, que lo utilizará para validar el tipo de incriptacion que 
	//    tiene las constraseñas del usuario.

	
	@Bean 
	public PasswordEncoder encriptador() {
		// return NoOpPasswordEncoder.getInstance(); --> este codigo permite leer contraseñas sin codificar.
		return new BCryptPasswordEncoder(); // --> valida que las constraseñas sean del tipo BCryptPasswordEncoder
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests()
				.requestMatchers("/img/**","/js/**","/css/**","/index","/login").permitAll()				
//				.requestMatchers("/listarEmpleado").hasRole("ADMIN")
			.and().formLogin().loginPage("/login").loginProcessingUrl("/login")
			.defaultSuccessUrl("/index").permitAll()
			.and().httpBasic();
		return http.build();
//		hasAnyAuthority("ADMIN", "USER")
//		hasAnyRole("ADMIN","USER")
	}
	
//	@Bean
//	public UserDetailsService userDetailService() { // necesita un usuario.
//		var user = User.withUsername("pepe").password("12345").roles("read").build();
//		return new InMemoryUserDetailsManager(user); // es una de las implementacion de UserDetailsService, subclase.
//	}
	
	
}
