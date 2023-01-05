package com.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.cibertec.model.Distrito;


@Repository
public interface DistritoRepository extends JpaRepository<Distrito, String> {
	
	/* Repository : se utiliza para administrar los datos en una aplicacion en Spring Boot
	 * JpaRepository : es particularmente una extensión específica de JPA para Repository. Tiene la API 
	 * completa CrudRepository y PagingAndSortingRepository. Así que, básicamente, Jpa Repository contiene 
	 * las APIs para las operaciones CRUD básicas, las APIS para la paginación y las APIs para la ordenación.
	 * */

}
