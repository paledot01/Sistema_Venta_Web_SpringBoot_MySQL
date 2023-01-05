package com.edu.cibertec.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String cod_estado;
	private String descripcion;
	
	// -----
	public Estado() {
	}
	public Estado(String cod_estado, String descripcion) {
		super();
		this.cod_estado = cod_estado;
		this.descripcion = descripcion;
	}

	// -----
	public String getCod_estado() {
		return cod_estado;
	}

	public void setCod_estado(String cod_estado) {
		this.cod_estado = cod_estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
