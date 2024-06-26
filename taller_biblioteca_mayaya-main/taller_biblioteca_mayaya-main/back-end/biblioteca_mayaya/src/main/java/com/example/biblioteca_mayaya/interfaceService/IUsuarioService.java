package com.example.biblioteca_mayaya.interfaceService;

import java.util.List;
import java.util.Optional;
import com.example.biblioteca_mayaya.models.usuario;

public interface IUsuarioService {

	public String save(usuario usuario);
	public List <usuario> findAll();
	public List<usuario> filtroUsuario(String filtro);
	public Optional<usuario>findOne (String id);
	public int delete (String id);
	public List<usuario>filtroIngresoUsuario(String numero_documento_usuario);
	
	
}