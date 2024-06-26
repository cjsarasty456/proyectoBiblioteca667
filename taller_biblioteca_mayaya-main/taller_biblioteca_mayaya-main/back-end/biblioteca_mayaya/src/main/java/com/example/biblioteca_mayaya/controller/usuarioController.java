package com.example.biblioteca_mayaya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.biblioteca_mayaya.interfaceService.IUsuarioService;
import com.example.biblioteca_mayaya.models.usuario;


@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin
public class usuarioController {
	
	@Autowired 
	
	private IUsuarioService usuarioService;
	/*
	 * retorna un json , indicando si funciono, presentó
	 * error, los datos solicitados
	 */
	@PostMapping("/")
public ResponseEntity<Object> save(@ModelAttribute("usuario") usuario usuario) {
	    
	    List<usuario> usuarios = usuarioService.filtroIngresoUsuario(usuario.getNumero_documento_usuario());
	    if (!usuarios.isEmpty()) {
	        return new ResponseEntity<>("El libro ya se encuentra registrado", HttpStatus.BAD_REQUEST);
	    }
	    if (usuario.getNumero_documento_usuario().equals("")) {

            return new ResponseEntity<>("El numero de documento es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getNombre_usuario().equals("")) {
            
            return new ResponseEntity<>("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getDireccion_usuario().equals("")) {
            
            return new ResponseEntity<>("La dirección es obligatoria", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getCorreo_electronico_usuario().equals("")) {
            
            return new ResponseEntity<>("El correo electrónico es obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (usuario.getTipo_usuario().equals("")) {

            return new ResponseEntity<>("El tipo de usuario es obligatorio", HttpStatus.BAD_REQUEST);
        }

        
        
		usuarioService.save(usuario);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var ListaUsuario = usuarioService.findAll();
		return new ResponseEntity<>(ListaUsuario, HttpStatus.OK);
	}
	
	//filtro
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
		var ListaUsuario = usuarioService.filtroUsuario(filtro);
		return new ResponseEntity<>(ListaUsuario, HttpStatus.OK);
	}
	//@PathVariable recibe una variable por el enlace
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<Object> findOne ( @PathVariable String id_usuario ){
		var usuario= usuarioService.findOne(id_usuario);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	

	
			@PutMapping("/{id_usuario}")
			public ResponseEntity<Object> update(@PathVariable String id_usuario, @ModelAttribute("usuario") usuario usuarioUpdate) {
			    
				// Verificar si hay campos vacíos
				
				if (usuarioUpdate.contieneCamposVacios()) {
					return new ResponseEntity<>("Todos los campos son obligatorios", HttpStatus.BAD_REQUEST);
				}

				var usuario = usuarioService.findOne(id_usuario).get();
				if (usuario != null) {
					  // Verificar si el titulo se está cambiando
			        if (!usuario.getNumero_documento_usuario().equals(usuarioUpdate.getNumero_documento_usuario())) {
			            // El titulo se está cambiando, verificar si ya está en uso
			            List<usuario> usuarios_con_mismo_documento = usuarioService.filtroIngresoUsuario(usuarioUpdate.getNumero_documento_usuario());
			            if (!usuarios_con_mismo_documento.isEmpty()) {
			                // Si hay otros libros con el mismo número de documento, devuelve un error
			                return new ResponseEntity<>("El usuario ya está registrado", HttpStatus.BAD_REQUEST);
			            }
			        }


					usuario.setNumero_documento_usuario(usuarioUpdate.getNumero_documento_usuario());
					usuario.setNombre_usuario(usuarioUpdate.getNombre_usuario());
					usuario.setDireccion_usuario(usuarioUpdate.getDireccion_usuario());
					usuario.setCorreo_electronico_usuario(usuarioUpdate.getCorreo_electronico_usuario());
					usuario.setTipo_usuario(usuarioUpdate.getTipo_usuario());

					usuarioService.save(usuario);
					return new ResponseEntity<>("Guardado", HttpStatus.OK);

				} else {
					return new ResponseEntity<>("Error usuario no encontrado", HttpStatus.BAD_REQUEST);
				}
			}
			
			@DeleteMapping("/{id_usuario}")
			public ResponseEntity<Object>delete (@PathVariable("id_usuario")String id_usuario){
				usuarioService.delete(id_usuario);
				return new ResponseEntity<>("Usuario eliminado",HttpStatus.OK);
			}
	

}
