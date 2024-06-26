package com.example.biblioteca_mayaya.interfaceService;

import java.util.List;
import java.util.Optional;
import com.example.biblioteca_mayaya.models.prestamo;

public interface IPrestamoService {
	public String save(prestamo prestamo);
	public List <prestamo> findAll();
	public List<prestamo> filtroPrestamo(String filtro);
	public Optional<prestamo>findOne (String id);
	public int delete (String id);

}