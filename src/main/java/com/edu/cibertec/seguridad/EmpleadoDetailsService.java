package com.edu.cibertec.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.cibertec.model.Empleado;
import com.edu.cibertec.model.Rol;
import com.edu.cibertec.repository.EmpleadoRepository;



@Service
public class EmpleadoDetailsService implements UserDetailsService{

	@Autowired
	private EmpleadoRepository empleadoRepo;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleado emp = empleadoRepo.findByUsuario(username);
		List<GrantedAuthority> autorizacion = new ArrayList<GrantedAuthority>();
		
		if(emp == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos GAAAA");
		}
		
		for(Rol rol : emp.getRoles() ) {
			autorizacion.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		System.out.println(emp);
		System.out.println(autorizacion);
		return new User(emp.getUsuario(), emp.getContrasena(), true, true, true, true, autorizacion); // user, pass, roles
	}
	
	
//	 @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		 	Empleado emp = empleadoRepo.findByUsuario(username);
//	        if (emp != null) {
//	            return new org.springframework.security.core.userdetails.User(emp.getUsuario()
//	                    , emp.getContrasena(),
//	                    emp.getRoles().stream()
//	                            .map((rol) -> new SimpleGrantedAuthority(rol.getNombre()))
//	                            .collect(Collectors.toList()));
//	        } else {
//	            throw new UsernameNotFoundException("Invalid email or password");
//	        }
//	    }
	
}
