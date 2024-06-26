package com.example.biblioteca_mayaya.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.biblioteca_mayaya.models.libro;

public interface ILibroService {

	public String save(libro libro);
	public List <libro> findAll();
	public List<libro> filtroLibro(String filtro);
	public Optional<libro>findOne (String id);
	public int delete (String id);
	public List<libro>filtroIngresoLibro(String titulo_libro);
	
	
}
