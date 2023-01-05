package com.edu.cibertec.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "distrito")
public class Distrito implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String cod_distrito;
	private String descripcion;
	
	// Constructores
	public Distrito() {
	}

	public Distrito(String cod_distrito, String descripcion) {
		super();
		this.cod_distrito = cod_distrito;
		this.descripcion = descripcion;
	}
	
	// GET - SET
	public String getCod_distrito() {
		return cod_distrito;
	}

	public void setCod_distrito(String cod_distrito) {
		this.cod_distrito = cod_distrito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
