package com.edu.cibertec.model;

import jakarta.validation.constraints.NotBlank;

public class EmpleadoPOJO {

	private String cod_empleado;
	private String cod_distrito;
	private String cod_estado;
	@NotBlank
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	private String usuario;
	private String contrasena;
	
	
	// --------------------------------------------------
	public EmpleadoPOJO() {
	}
	
	public EmpleadoPOJO(String cod_empleado, String cod_distrito, String cod_estado, String nombre, String apellidos,
			String dni, String direccion, String telefono, String email, String usuario, String contrasena) {
		super();
		this.cod_empleado = cod_empleado;
		this.cod_distrito = cod_distrito;
		this.cod_estado = cod_estado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	
	// ---------------------------------------------------
	public String getCod_empleado() {
		return cod_empleado;
	}

	public void setCod_empleado(String cod_empleado) {
		this.cod_empleado = cod_empleado;
	}

	public String getCod_distrito() {
		return cod_distrito;
	}

	public void setCod_distrito(String cod_distrito) {
		this.cod_distrito = cod_distrito;
	}

	public String getCod_estado() {
		return cod_estado;
	}

	public void setCod_estado(String cod_estado) {
		this.cod_estado = cod_estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "EmpleadoPOJO [cod_empleado=" + cod_empleado + ", cod_distrito=" + cod_distrito + ", cod_estado="
				+ cod_estado + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", direccion="
				+ direccion + ", telefono=" + telefono + ", email=" + email + ", usuario=" + usuario + ", contrasena="
				+ contrasena + "]";
	}
	
	
}
