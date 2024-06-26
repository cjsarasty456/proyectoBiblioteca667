package com.example.biblioteca_mayaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.biblioteca_mayaya.interfaceService.ILibroService;
import com.example.biblioteca_mayaya.interfaces.ILibro;
import com.example.biblioteca_mayaya.models.libro;

@Service
public class libroService implements ILibroService{
	
	@Autowired 
	private ILibro data;
	
	@Override
	public String save(libro libro) {
		data.save(libro);
		return libro.getId_libro();
	}

	@Override
	public List<libro> findAll() {
		List <libro> listaLibro = (List<libro>) data.findAll() ;
		
		return listaLibro;
	}

	@Override
	public List<libro> filtroLibro(String filtro) {
		List <libro> listaLibro=data.filtroLibro(filtro);
		return listaLibro;
	}
	
	
	@Override
	public Optional<libro> findOne(String id_libro) {
		Optional<libro>libro=data.findById(id_libro);
		
		return libro;
	}

	@Override
	public int delete(String id_libro) {
		data.deleteById(id_libro);
		return 1;
	}
	@Override

	public List<libro> filtroIngresoLibro(String titulo_libro) {
		List<libro> listaLibro=data.filtroIngresoLibro(titulo_libro);
		return listaLibro;
	}
	

}
