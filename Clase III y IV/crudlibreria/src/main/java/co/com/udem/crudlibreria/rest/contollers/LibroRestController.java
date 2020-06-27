package co.com.udem.crudlibreria.rest.contollers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.crudlibreria.dto.LibroDTO;
import co.com.udem.crudlibreria.entities.Libro;
import co.com.udem.crudlibreria.repositories.LibroRepository;
import co.com.udem.crudlibreria.util.ConvertLibro;

@RestController
public class LibroRestController {

	@Autowired
	private LibroRepository libroRepository;

	private ConvertLibro convertLibro = new ConvertLibro();

	@PostMapping("/libros/adicionarLibro")
	public ResponseEntity<String> adicionarUsuario(@RequestBody LibroDTO libroDTO) {
		Libro libro;
		try {
			libro = convertLibro.convertToEntity(libroDTO);
			libroRepository.save(libro);
		} catch (ParseException e) {
			System.err.println("Error convirtiendo a entity: " + e.getMessage() + e.getCause());
		}

		return ResponseEntity.ok("Registro guardado de forma exitosa");
	}

	@GetMapping("/libros")
	public Iterable<Libro> listarUsuarios() {
		return libroRepository.findAll();
	}

	@GetMapping("/libros/{id}")
	public Libro buscarUsuario(@PathVariable Long id) {
		return libroRepository.findById(id).get();
	}

	@DeleteMapping("/libros/{id}")
	public ResponseEntity<String> eliminarLibros(@PathVariable Long id) {
		libroRepository.deleteById(id);
		return ResponseEntity.ok("Registro eliminado de forma exitosa");
	}

}
