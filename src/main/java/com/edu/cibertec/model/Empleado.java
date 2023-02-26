package com.edu.cibertec.model;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String cod_empleado;
	@ManyToOne
	@JoinColumn(name = "cod_distrito")
	private Distrito distrito;
	@ManyToOne
	@JoinColumn(name = "cod_estado")
	private Estado estado;
	//@Column(nullable = false)
	@NotBlank // no null no vacion ""
	private String nombre;
	@NotBlank
	private String apellidos;
	@NotBlank
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	@NotBlank
	private String usuario;
	@NotBlank // cuando se codifica llega a 60 caracteres creo no se, mas o menos :D
	private String contrasena;

	@ManyToMany(fetch = FetchType.EAGER)//cascade = CascadeType.ALL
	@JoinTable(name = "empleado_rol", // Nombre de la tabla intermedia
			joinColumns = @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado"), // el primer cod_empleado hace referencia a la tabla intermedia, el segundo a esta tabla empleado
			inverseJoinColumns = @JoinColumn(name = "cod_rol", referencedColumnName = "cod_rol") // el 2do cod_rol hace referencia al pk de la tabla Rol
	)
	private Collection<Rol> roles; // este campo no esta realmente en la tabla

	
	// CONSTRUCTORES ------------------------------------------------------
	public Empleado() {
	}
	public Empleado(String cod_empleado, Distrito distrito, Estado estado, String nombre, String apellidos, String dni,
			String direccion, String telefono, String email, String usuario, String contrasena, Collection<Rol> roles) {
		super();
		this.cod_empleado = cod_empleado;
		this.distrito = distrito;
		this.estado = estado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.roles = roles;
	}

	// --------------------------------------------------------------------

	public String getCod_empleado() {
		return cod_empleado;
	}

	public void setCod_empleado(String cod_empleado) {
		this.cod_empleado = cod_empleado;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "Empleado [cod_empleado=" + cod_empleado + ", distrito=" + distrito + ", estado=" + estado + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", direccion=" + direccion + ", telefono="
				+ telefono + ", email=" + email + ", usuario=" + usuario + ", contrasena=" + contrasena + ", roles="
				+ roles + "]";
	}
	
	
}
