package com.example.biblioteca_mayaya.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.biblioteca_mayaya.models.prestamo;

@Repository
public interface IPrestamo extends CrudRepository <prestamo,String>{
	
	@Query("SELECT p FROM prestamo p JOIN "
			+"p.usuario u "
			+"JOIN p.libro l "
			+"WHERE u.nombre_usuario LIKE %?1% "
			+"OR l.titulo_libro LIKE %?1% "
			+"OR p.fecha_prestamo= ?1 "
			+"OR p.fecha_devolucion= ?1 "
			+"OR estado_prestamo LIKE %?1%")
	List<prestamo>filtroPrestamo (String filtro);
	
}