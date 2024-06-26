package com.example.biblioteca_mayaya.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.biblioteca_mayaya.models.libro;

@Repository
public interface ILibro extends CrudRepository <libro,String> {
	
	@Query("SELECT l FROM libro l WHERE "
			+ "l.titulo_libro LIKE %?1% OR "
			+ "l.autor_libro LIKE %?1% OR "
			+ "l.isbn_libro LIKE %?1% OR "
			+ "l.genero_libro LIKE %?1% OR "
			+ "l.numero_ejemplares_disponibles LIKE %?1% OR "
			+ "l.numero_ejemplares_ocupados LIKE %?1%")
	List<libro>filtroLibro (String filtro);
	
	@Query("SELECT l FROM libro l WHERE l.isbn_libro = ?1")
	List<libro> filtroIngresoLibro(String isbn_libro);

}
