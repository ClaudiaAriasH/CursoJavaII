package co.com.udem.crud.rest.contollers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.crud.dto.UsuarioDTO;
import co.com.udem.crud.entities.Usuario;
import co.com.udem.crud.repositories.UsuarioRepository;
import co.com.udem.crud.util.ConvertUsuario;

@RestController
public class UsuarioRestController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private ConvertUsuario convertUsuario = new ConvertUsuario();

	@PostMapping("/usuarios/adicionarUsuario")
	public ResponseEntity<String> adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario;
		try {
			usuario = convertUsuario.convertToEntity(usuarioDTO);
			usuarioRepository.save(usuario);
		} catch (ParseException e) {
			System.err.println("Error convirtiendo la DTO en entity: " + e.getMessage() + e.getCause());
		}

		return ResponseEntity.ok("Registro guardado de forma exitosa");
	}

	@GetMapping("/usuarios")
	public Iterable<Usuario> listarUsuarios() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		Iterable<Usuario> usuario = usuarioRepository.findAll();
		return usuario;
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		return ResponseEntity.ok("Registro eliminado de forma exitosa");
	}

	@GetMapping("/usuarios/{id}")
	public UsuarioDTO buscarUsuario(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		try {
			Usuario usuario = usuarioRepository.findById(id).get();
			usuarioDTO = convertUsuario.convertToDTO(usuario);
		} catch (ParseException e) {
			System.err.println("Error convirtiendo a entity a DTO: " + e.getMessage() + e.getCause());
		}
		return usuarioDTO;
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody Usuario newUser, @PathVariable Long id) {
		if (usuarioRepository.findById(--id).isPresent()) {
			Usuario user = usuarioRepository.findById(id).get();
			user.setFirsName(newUser.getFirsName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			usuarioRepository.save(user);
			return ResponseEntity.ok("Se actualizó exitosamente");
		} else {
			return null;
		}
	}
}
