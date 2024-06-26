package com.example.biblioteca_mayaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biblioteca_mayaya.interfaceService.IUsuarioService;
import com.example.biblioteca_mayaya.interfaces.IUsuario;
import com.example.biblioteca_mayaya.models.usuario;

@Service
public class usuarioService implements IUsuarioService{
	
	@Autowired 
	private IUsuario data;
	
	@Override
	public String save(usuario usuario) {
		data.save(usuario);
		return usuario.getId_usuario();
	}

	@Override
	public List<usuario> findAll() {
		List <usuario> listaUsuario = (List<usuario>) data.findAll() ;
		
		return listaUsuario;
	}

	@Override
	public List<usuario> filtroUsuario(String filtro) {
		List <usuario> listaUsuario=data.filtroUsuario(filtro);
		return listaUsuario;
	}
	
	
	@Override
	public Optional<usuario> findOne(String id_usuario) {
		Optional<usuario>usuario=data.findById(id_usuario);
		
		return usuario;
	}

	@Override
	public int delete(String id_usuario) {
		data.deleteById(id_usuario);
		return 1;
	}
	@Override

	public List<usuario> filtroIngresoUsuario(String numero_documento_usuario) {
		List<usuario> listaUsuario=data.filtroIngresoUsuario(numero_documento_usuario);
		return listaUsuario;
	}
	

}
