package com.example.biblioteca_mayaya.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="id_usuario",nullable=false,length=36)
	private String id_usuario;
	
	@Column(name="numero_documento_usuario",nullable=false,length=40)
	private String numero_documento_usuario;
	
	@Column(name="nombre_usuario",nullable=false,length=100)
	private String nombre_usuario;
	
	@Column(name="direccion_usuario",nullable=false,length=40)
	private String direccion_usuario;
	
	@Column(name="correo_electronico_usuario",nullable=false,length=40)
	private String correo_electronico_usuario;
	
	@Column(name="tipo_usuario",nullable=false,length=40)
	private String tipo_usuario;

	public usuario() {
		super();
	}

	public usuario(String id_usuario, String numero_documento_usuario, String nombre_usuario, String direccion_usuario,
			String correo_electronico_usuario, String tipo_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.numero_documento_usuario = numero_documento_usuario;
		this.nombre_usuario = nombre_usuario;
		this.direccion_usuario = direccion_usuario;
		this.correo_electronico_usuario = correo_electronico_usuario;
		this.tipo_usuario = tipo_usuario;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNumero_documento_usuario() {
		return numero_documento_usuario;
	}

	public void setNumero_documento_usuario(String numero_documento_usuario) {
		this.numero_documento_usuario = numero_documento_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getDireccion_usuario() {
		return direccion_usuario;
	}

	public void setDireccion_usuario(String direccion_usuario) {
		this.direccion_usuario = direccion_usuario;
	}

	public String getCorreo_electronico_usuario() {
		return correo_electronico_usuario;
	}

	public void setCorreo_electronico_usuario(String correo_electronico_usuario) {
		this.correo_electronico_usuario = correo_electronico_usuario;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public boolean contieneCamposVacios() {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}

