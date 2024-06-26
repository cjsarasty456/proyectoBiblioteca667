package com.example.biblioteca_mayaya.models;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class libro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="id_libro",nullable=false,length=36)
	private String id_libro;
	
	
	@Column (name="titulo_libro",nullable=false,length=40)
	private String titulo_libro;
	
	
	@Column (name="autor_libro",nullable=false,length=40)
	private String autor_libro;
	
	@Column (name="isbn_libro",nullable=false,length=40)
	private String isbn_libro;
	
	@Column (name="genero_libro",nullable=false,length=40)
	private String genero_libro;
	
	@Column (name="numero_ejemplares_disponibles",nullable=false,length=40)
	private String numero_ejemplares_disponibles;
	
	@Column (name="numero_ejemplares_ocupados",nullable=false,length=40)
	private String numero_ejemplares_ocupados;

	public libro() {
		super();
	}

	public libro(String id_libro, String titulo_libro, String autor_libro, String isbn_libro, String genero_libro,
			String numero_ejemplares_disponibles, String numero_ejemplares_ocupados) {
		super();
		this.id_libro = id_libro;
		this.titulo_libro = titulo_libro;
		this.autor_libro = autor_libro;
		this.isbn_libro = isbn_libro;
		this.genero_libro = genero_libro;
		this.numero_ejemplares_disponibles = numero_ejemplares_disponibles;
		this.numero_ejemplares_ocupados = numero_ejemplares_ocupados;
	}

	public String getId_libro() {
		return id_libro;
	}

	public void setId_libro(String id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo_libro() {
		return titulo_libro;
	}

	public void setTitulo_libro(String titulo_libro) {
		this.titulo_libro = titulo_libro;
	}

	public String getAutor_libro() {
		return autor_libro;
	}

	public void setAutor_libro(String autor_libro) {
		this.autor_libro = autor_libro;
	}

	public String getIsbn_libro() {
		return isbn_libro;
	}

	public void setIsbn_libro(String isbn_libro) {
		this.isbn_libro = isbn_libro;
	}

	public String getGenero_libro() {
		return genero_libro;
	}

	public void setGenero_libro(String genero_libro) {
		this.genero_libro = genero_libro;
	}

	public String getNumero_ejemplares_disponibles() {
		return numero_ejemplares_disponibles;
	}

	public void setNumero_ejemplares_disponibles(String numero_ejemplares_disponibles) {
		this.numero_ejemplares_disponibles = numero_ejemplares_disponibles;
	}

	public String getNumero_ejemplares_ocupados() {
		return numero_ejemplares_ocupados;
	}

	public void setNumero_ejemplares_ocupados(String numero_ejemplares_ocupados) {
		this.numero_ejemplares_ocupados = numero_ejemplares_ocupados;
	}

	public boolean contieneCamposVacios() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	

}
