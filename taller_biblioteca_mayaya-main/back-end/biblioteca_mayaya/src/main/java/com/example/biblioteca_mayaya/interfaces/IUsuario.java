package com.example.biblioteca_mayaya.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.biblioteca_mayaya.models.usuario;

@Repository
public interface IUsuario extends CrudRepository <usuario,String> {
	
	@Query("SELECT u FROM usuario u WHERE "
			+ "u.numero_documento_usuario LIKE %?1% OR "
			+ "u.nombre_usuario LIKE %?1% OR "
			+ "u.direccion_usuario LIKE %?1% OR "
			+ "u.correo_electronico_usuario LIKE %?1% OR "
			+ "u.tipo_usuario LIKE %?1%")
	List<usuario>filtroUsuario (String filtro);
	
	@Query("SELECT u FROM usuario u WHERE u.numero_documento_usuario = ?1")
	List<usuario> filtroIngresoUsuario(String numero_documento_usuario);

}