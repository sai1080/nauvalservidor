package com.simonsoft.nauval.modelo;


/**
 * Clase del modelo de dominio que representa la entidad ClubNautico
 * @author Simón
 *
 */
public class ClubNautico {
	private Integer id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String web;
	private Double longitud;
	private Double latitud;
	
public ClubNautico(){
		
	}
	
	public ClubNautico(Integer id, String nombre, String direccion,
			String telefono, String email, String web, Double longitud,
			Double latitud) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.web = web;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	
	/**
	 * Recupera la id del club nautico
	 * 
	 * @return
	 * La id del club nautico
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Establece el valor del atributo id
	 * @param id El nuevo valor
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	@Override
	public String toString() {
		
		return nombre;
	}

}
