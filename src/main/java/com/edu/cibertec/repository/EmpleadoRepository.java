package com.edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.cibertec.model.Empleado;
import com.edu.cibertec.model.EmpleadoReportePOJO;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

	
	// INNER JOIN con JPQL
	@Query("SELECT new com.edu.cibertec.model.EmpleadoReportePOJO("
			+ "e.cod_empleado,"
			+ "e.distrito.descripcion,"
			+ "e.estado.descripcion,"
			+ "e.nombre,"
			+ "e.apellidos,"
			+ "e.dni,"
			+ "e.direccion,"
			+ "e.telefono,"
			+ "e.email,"
			+ "e.usuario,"
			+ "e.contrasena"
			+ ") "
			+ "FROM Empleado e ORDER BY e.cod_empleado ASC")
	public abstract List<EmpleadoReportePOJO> listaPOJO();
	
	
	// CONSULTA NATIVA --> el 3 incluye el 3er caracter
	@Query(value="SELECT SUBSTRING(MAX(e.cod_empleado),3) FROM empleado AS e", nativeQuery = true)
	public abstract String getUltimoCodigo();
	
	public Empleado findByUsuario(String usuario); // automatico
	
}
