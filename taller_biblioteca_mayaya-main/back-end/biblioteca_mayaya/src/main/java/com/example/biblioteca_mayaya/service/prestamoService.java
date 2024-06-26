package com.example.biblioteca_mayaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca_mayaya.interfaceService.IPrestamoService;
import com.example.biblioteca_mayaya.interfaces.IPrestamo;
import com.example.biblioteca_mayaya.models.prestamo;

@Service
public class prestamoService implements IPrestamoService{


	@Autowired 
	private IPrestamo data;
	
	@Override
	public String save(prestamo prestamo) {
		data.save(prestamo);
		return prestamo.getId_prestamo();
	}

	@Override
	public List<prestamo> findAll() {
		List <prestamo> listaPrestamo = (List<prestamo>) data.findAll() ;
		
		return listaPrestamo;
	}

	@Override
	public List<prestamo> filtroPrestamo(String filtro) {
		List <prestamo> listaPrestamo=data.filtroPrestamo(filtro);
		return listaPrestamo;
	}
	
	
	@Override
	public Optional<prestamo> findOne(String id_prestamo) {
		Optional<prestamo>prestamo=data.findById(id_prestamo);
		
		return prestamo;
	}

	@Override
	public int delete(String id_prestamo) {
		data.deleteById(id_prestamo);
		return 1;
	}
	
	

}
