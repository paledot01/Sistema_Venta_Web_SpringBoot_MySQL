package com.edu.cibertec.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String cod_rol;
	private String nombre;
	
	
	
	// ----
	public Rol() {
		super();
	}
	public Rol(String cod_rol, String nombre) {
		super();
		this.cod_rol = cod_rol;
		this.nombre = nombre;
	}
	
	// ----
	public String getCod_rol() {
		return cod_rol;
	}
	public void setCod_rol(String cod_rol) {
		this.cod_rol = cod_rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public String toString() {
		return "Rol [cod_rol=" + cod_rol + ", nombre=" + nombre + "]";
	}
	
	
}
