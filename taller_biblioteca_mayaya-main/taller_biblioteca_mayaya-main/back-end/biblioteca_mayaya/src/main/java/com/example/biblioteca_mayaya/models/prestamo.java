package com.example.biblioteca_mayaya.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class prestamo {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="id_prestamo",nullable=false,length=36)
	private String id_prestamo;
	
	
	@ManyToOne
	@JoinColumn (name="id_usuario")
	private usuario usuario;
	
	
	@ManyToOne
	@JoinColumn (name="id_libro")
	private libro libro;
	
	@Column(name="fecha_prestamo",nullable=false,length=36)
	private String fecha_prestamo;
	
	@Column(name="fecha_devolucion",nullable=false,length=36)
	private String fecha_devolucion;
	
	@Column(name="estado_prestamo",nullable=false,length=36)
	private String estado_prestamo;

	public prestamo() {
		super();
	}

	public prestamo(String id_prestamo, com.example.biblioteca_mayaya.models.usuario usuario,
			com.example.biblioteca_mayaya.models.libro libro, String fecha_prestamo, String fecha_devolucion,
			String estado_prestamo) {
		super();
		this.id_prestamo = id_prestamo;
		this.usuario = usuario;
		this.libro = libro;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
		this.estado_prestamo = estado_prestamo;
	}

	public String getId_prestamo() {
		return id_prestamo;
	}

	public void setId_prestamo(String id_prestamo) {
		this.id_prestamo = id_prestamo;
	}

	public usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(usuario usuario) {
		this.usuario = usuario;
	}

	public libro getLibro() {
		return libro;
	}

	public void setLibro(libro libro) {
		this.libro = libro;
	}

	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public String getEstado_prestamo() {
		return estado_prestamo;
	}

	public void setEstado_prestamo(String estado_prestamo) {
		this.estado_prestamo = estado_prestamo;
	}

	public boolean contieneCamposVacios() {
		// TODO Auto-generated method stub
		return false;
	}

	
	

	
	
	
	
}
