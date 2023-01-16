package com.clases.springboot.app.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clases.springboot.app.models.entity.Role;
import com.clases.springboot.app.models.entity.Usuario;


@Service("JpaUserDetailsService")
public class JpaUserDetailService implements UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger= LoggerFactory.getLogger(JpaUserDetailService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario==null) {
			logger.error("Error en Login: no existe el usuario ->" + username);
			throw new UsernameNotFoundException("Error: "+username+ "no tiene roles");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role: usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if (authorities.isEmpty()) {
			logger.error("Error en el login: " +username+" no tiene roles asignados");
			throw new UsernameNotFoundException("Error en el logion: "+username+" no tines roles ");
		}
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), 
				true , true , true , authorities );
	}

}
