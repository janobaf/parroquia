package com.clases.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.clases.springboot.app.models.dao.JpaUserDetailService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build)
	throws Exception{		
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		///permitir rutas publicas y luego las privadas
		http.authorizeRequests()
		.antMatchers("/","/css/**","/js/**","/panelControl").permitAll()
		//.antMatchers("/").permitAll()
		
		.antMatchers("/uploads/**").hasAnyRole("USER")
		.antMatchers("/listarActa").hasAnyRole("USER")
		.antMatchers("/reportMes").hasAnyRole("USER")
		.antMatchers("/reportAno").hasAnyRole("USER")
		.antMatchers("/listarLibro").hasAnyRole("USER")
		.antMatchers("/listarAgenda").hasAnyRole("USER")
		.antMatchers("/listarCliente").hasAnyRole("USER")
		.antMatchers("/formLibro").hasAnyRole("USER")
		.antMatchers("/formCliente").hasAnyRole("USER")
		.antMatchers("/formLibro").hasAnyRole("USER")
		.antMatchers("/formLibroBautizo").hasAnyRole("USER")
		.antMatchers("/formLibroConfirmacion").hasAnyRole("USER")
		.antMatchers("/formLibroMarimonio").hasAnyRole("USER")
		.antMatchers("/listarAgenda").hasAnyRole("USER")
		.antMatchers("/listarLibroBautizo").hasAnyRole("USER")
		.antMatchers("/listarLibroConfirmacion").hasAnyRole("USER")
		.antMatchers("/listarLibroMarimonio").hasAnyRole("USER")
		
		.antMatchers("/listarRole/**").hasAnyRole("ADMIN")
		.antMatchers("/listarUsuario/**").hasAnyRole("ADMIN")
		.antMatchers("/listarEmpleado/**").hasAnyRole("ADMIN")
		.antMatchers("/formRole/**").hasAnyRole("ADMIN")
		.antMatchers("/formUsuario/**").hasAnyRole("ADMIN")
		.antMatchers("/formEmpleado/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/panelControl")
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
		
	}	
}
