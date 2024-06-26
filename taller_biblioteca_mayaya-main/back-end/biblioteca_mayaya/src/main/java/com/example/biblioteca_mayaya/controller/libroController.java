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

import com.example.biblioteca_mayaya.interfaceService.ILibroService;
import com.example.biblioteca_mayaya.models.libro;


@RestController
@RequestMapping("/api/v1/libro")
@CrossOrigin
public class libroController {
	
	@Autowired 
	
	private ILibroService libroService;
	/*
	 * retorna un json , indicando si funciono, presentó
	 * error, los datos solicitados
	 */
	@PostMapping("/")
public ResponseEntity<Object> save(@ModelAttribute("libro") libro libro) {
	    
	    List<libro> libros = libroService.filtroIngresoLibro(libro.getTitulo_libro());
	    if (!libros.isEmpty()) {
	        return new ResponseEntity<>("El libro ya se encuentra registrado", HttpStatus.BAD_REQUEST);
	    }
	    if (libro.getTitulo_libro().equals("")) {

            return new ResponseEntity<>("El titulo del libro es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getAutor_libro().equals("")) {
            
            return new ResponseEntity<>("El autor del libro es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getIsbn_libro().equals("")) {
            
            return new ResponseEntity<>("El ISBN del libro es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (libro.getNumero_ejemplares_disponibles().equals("")) {
            
            return new ResponseEntity<>("El numero de ejemplares disponibles es obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (libro.getNumero_ejemplares_ocupados().equals("")) {

            return new ResponseEntity<>("El numero de ejemplares ocupados es obligatorio", HttpStatus.BAD_REQUEST);
        }

        
        
		libroService.save(libro);
		return new ResponseEntity<>(libro,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var ListaLibro = libroService.findAll();
		return new ResponseEntity<>(ListaLibro, HttpStatus.OK);
	}
	
	//filtro
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
		var ListaLibro = libroService.filtroLibro(filtro);
		return new ResponseEntity<>(ListaLibro, HttpStatus.OK);
	}
	//@PathVariable recibe una variable por el enlace
	
	@GetMapping("/{id_libro}")
	public ResponseEntity<Object> findOne ( @PathVariable String id_libro ){
		var libro= libroService.findOne(id_libro);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}
	
	

	
			@PutMapping("/{id_libro}")
			public ResponseEntity<Object> update(@PathVariable String id_libro, @ModelAttribute("libro") libro libroUpdate) {
			    
				// Verificar si hay campos vacíos
				
				if (libroUpdate.contieneCamposVacios()) {
					return new ResponseEntity<>("Todos los campos son obligatorios", HttpStatus.BAD_REQUEST);
				}

				var libro = libroService.findOne(id_libro).get();
				if (libro != null) {
					  // Verificar si el titulo se está cambiando
			        if (!libro.getTitulo_libro().equals(libroUpdate.getTitulo_libro())) {
			            // El titulo se está cambiando, verificar si ya está en uso
			            List<libro> libros_con_mismo_titulo = libroService.filtroIngresoLibro(libroUpdate.getTitulo_libro());
			            if (!libros_con_mismo_titulo.isEmpty()) {
			                // Si hay otros libros con el mismo número de documento, devuelve un error
			                return new ResponseEntity<>("El libro ya está registrado", HttpStatus.BAD_REQUEST);
			            }
			        }


					libro.setTitulo_libro(libroUpdate.getTitulo_libro());
					libro.setAutor_libro(libroUpdate.getAutor_libro());
					libro.setIsbn_libro(libroUpdate.getIsbn_libro());
					libro.setGenero_libro(libroUpdate.getGenero_libro());
					libro.setNumero_ejemplares_disponibles(libroUpdate.getNumero_ejemplares_disponibles());
					libro.setNumero_ejemplares_ocupados(libroUpdate.getNumero_ejemplares_ocupados());

					libroService.save(libro);
					return new ResponseEntity<>("Guardado", HttpStatus.OK);

				} else {
					return new ResponseEntity<>("Error libro no encontrado", HttpStatus.BAD_REQUEST);
				}
			}
			
			@DeleteMapping("/{id_libro}")
			public ResponseEntity<Object>delete (@PathVariable("id_libro")String id_libro){
				libroService.delete(id_libro);
				return new ResponseEntity<>("Libro eliminado",HttpStatus.OK);
			}
	

}
