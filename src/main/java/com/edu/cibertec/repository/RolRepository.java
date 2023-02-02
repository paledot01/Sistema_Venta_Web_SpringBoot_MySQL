package com.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, String>{

	
}
