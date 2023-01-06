package com.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.model.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, String>{

}
