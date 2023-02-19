package com.edu.cibertec.seguridad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity // si no lo anoto en alguna de mis clases la aplicacion pedirá nombre de usuario y contraseña
public class WebSecurityConfiguration {

	// @Bean es una anotacion a nivel de metodo que es analago del elemento XML <bean/>
	// 1. necesita un usuario y un codificador, la interfaz papa es PasswordEncoder.
	// 2. necesita un codificador, que lo utilizará para validar el tipo de incriptacion que 
	//    tiene las constraseñas del usuario.

	@Autowired
	private EmpleadoDetailsService empleadoDetails;
	
	@Autowired
	private LoginSucessHandler sucessHandler;
	

    
	@Bean 
	public PasswordEncoder encriptador() {
		// return NoOpPasswordEncoder.getInstance(); --> este codigo permite leer contraseñas sin codificar.
		return new BCryptPasswordEncoder(); // --> valida que las constraseñas sean del tipo BCryptPasswordEncoder
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		// Tenga en cuenta que el orden de los elementos antMatchers() es significativo; las reglas más 
		// específicas deben ir primero, seguidas de las más generales(permitAll)
		http.httpBasic().and().authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST,"/empleados/**").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET,"/empleados/**").hasAnyRole("ADMIN","USER")
				.requestMatchers("/").hasAnyRole("ADMIN","USER")
				.requestMatchers("/img/**","/js/**","/css/**").permitAll()
			.and().formLogin().successHandler(sucessHandler).loginPage("/login")//.loginProcessingUrl("/login")
			.defaultSuccessUrl("/",true).permitAll()
			.and().logout().clearAuthentication(true).invalidateHttpSession(true).logoutSuccessUrl("/login?logout").permitAll() //.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID") // url[logout es el parametro que se envia] - para que no invalide la session - eliminar cookies.
			.and().csrf().disable(); // <-------- CSRF bloquea los metodos POST, y esta habilitado por defecto
		return http.build();
	}
	

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(empleadoDetails);
		auth.setPasswordEncoder(encriptador());
		return auth;
	}
	
	
	
//	@Autowired
//  private DataSource dataSource;
//	
//    @Bean
//    public UserDetailsManager users(HttpSecurity http) throws Exception {
//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
//            .userDetailsService(empleadoDetails)
//            .passwordEncoder(encriptador())
//            .and()
//            .authenticationProvider(authenticationProvider())
//            .build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
//        return jdbcUserDetailsManager;
//    }
	
	
	
	
	
// --> CREADO UN USUARIO CON ROLES BASIC AUTOMATICO [ kevin, 123 ]
//	@Bean
//	public UserDetailsService userDetailService() { // necesita un usuario.
//		var user = User.withUsername("kevin").password("$2a$10$XoXoVkCDdDGeMHkDfM0/8.Q6Einje7.SWZiyggAv/XMMNKw89wFI2").roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(user); // es una de las implementacion de UserDetailsService, subclase.
//	}
	
	
}
