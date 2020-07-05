package co.com.udem.agenciainmobiliaria.rest.contoller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.agenciainmobiliaria.dto.RegistrarUsuarioDTO;
import co.com.udem.agenciainmobiliaria.dto.TipoIdentificacionDTO;
import co.com.udem.agenciainmobiliaria.entities.RegistrarUsuario;
import co.com.udem.agenciainmobiliaria.entities.TipoIdentificacion;
import co.com.udem.agenciainmobiliaria.repositories.RegistrarUsuarioRepository;
import co.com.udem.agenciainmobiliaria.repositories.TipoIdentificacionRepository;
import co.com.udem.agenciainmobiliaria.util.Constantes;
import co.com.udem.agenciainmobiliaria.util.ConvertRegistrarUsuario;
import co.com.udem.agenciainmobiliaria.util.ConvertTipoIdentificacion;

@RestController
public class AgenciaInmobiliariaRestController {

	@Autowired
	private RegistrarUsuarioRepository registrarUsuarioRepository;
	@Autowired
	private ConvertRegistrarUsuario convertRegistrarUsuario;

	@Autowired
	private TipoIdentificacionRepository tipoIdentificacionRepository;
	@Autowired
	private ConvertTipoIdentificacion convertTipoIdentificacion;

	@PostMapping("/agenciaInmobiliaria/adicionarTipoDocumento")
	public Map<String, String> adicionarTipoDocumento(@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			TipoIdentificacion tipoIdentificacion = convertTipoIdentificacion.convertToEntity(tipoIdentificacionDTO);
			tipoIdentificacionRepository.save(tipoIdentificacion);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registrado insertado exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar");
			return response;
		}

	}

	@GetMapping("/tiposDocumentos")
	public Iterable<TipoIdentificacion> listarTiposDocumentos() {

		Iterable<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepository.findAll();
		return tipoIdentificacion;
	}

	@DeleteMapping("/tiposDocumentos/{id}")
	public ResponseEntity<String> eliminarTipoDocumento(@PathVariable Long id) {

		tipoIdentificacionRepository.deleteById(id);
		return ResponseEntity.ok("Registro eliminado de forma exitosa");
	}

	@GetMapping("/tiposDocumentos/{id}")
	public TipoIdentificacionDTO buscarTipoDocumento(@PathVariable Long id) {
		TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();

		try {
			TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(id).get();
			tipoIdentificacionDTO = convertTipoIdentificacion.convertToDTO(tipoIdentificacion);
		} catch (ParseException e) {
			System.err.println("Error convirtiendo a entity a DTO: " + e.getMessage() + e.getCause());
		}
		return tipoIdentificacionDTO;
	}

	@PutMapping("/tiposDocumentos/{id}")
	public Map<String, String> updateTipoDocumento(@RequestBody TipoIdentificacion newTipoDoc, @PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		boolean dato = tipoIdentificacionRepository.findById(id).isPresent();
		System.err.println("Error Actualizando" + dato);
		if (tipoIdentificacionRepository.findById(id).isPresent()) {
			TipoIdentificacion tipoDoc = tipoIdentificacionRepository.findById(id).get();

			tipoDoc.setTipoDocumento(newTipoDoc.getTipoDocumento());
			tipoDoc.setDescripcion(newTipoDoc.getDescripcion());
			tipoIdentificacionRepository.save(tipoDoc);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registro actualizado exitosamente");
			return response;
		}
		response.put(Constantes.CODIGO_HTTP, "500");
		response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al actualizar el registro");
		return response;

	}

	@PostMapping("/agenciaInmobiliaria/adicionarUsuario")
	public Map<String, String> adicionarUsuario(@RequestBody RegistrarUsuarioDTO registrarUsuarioDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			RegistrarUsuario registrarUsuario = convertRegistrarUsuario.convertToEntity(registrarUsuarioDTO);
			registrarUsuarioRepository.save(registrarUsuario);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registrado insertado exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar");
			return response;
		}

	}

	@GetMapping("/usuarios")
	public Iterable<RegistrarUsuario> listarUsuarios() {

		Iterable<RegistrarUsuario> registrarUsuario = registrarUsuarioRepository.findAll();
		return registrarUsuario;
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {

		registrarUsuarioRepository.deleteById(id);
		return ResponseEntity.ok("Registro eliminado de forma exitosa");
	}

	@GetMapping("/usuarios/{id}")
	public RegistrarUsuarioDTO buscarUsuario(@PathVariable Long id) {
		RegistrarUsuarioDTO registrarUsuarioDTO = new RegistrarUsuarioDTO();

		try {
			RegistrarUsuario usuario = registrarUsuarioRepository.findById(id).get();
			registrarUsuarioDTO = convertRegistrarUsuario.convertToDTO(usuario);
		} catch (ParseException e) {
			System.err.println("Error convirtiendo a entity a DTO: " + e.getMessage() + e.getCause());
		}
		return registrarUsuarioDTO;
	}

	@PutMapping("/usuarios/{id}")
	public Map<String, String> updateUser(@RequestBody RegistrarUsuario newUser, @PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		boolean dato = registrarUsuarioRepository.findById(id).isPresent();
		System.err.println("Error Actualizando" + dato);
		if (registrarUsuarioRepository.findById(id).isPresent()) {
			RegistrarUsuario user = registrarUsuarioRepository.findById(id).get();
			user.setApellidos(newUser.getApellidos());
			user.setNombres(newUser.getNombres());
			user.setDireccion(newUser.getDireccion());
			user.setEmail(newUser.getEmail());
			user.setNumeroIdentificacion(newUser.getNumeroIdentificacion());
			user.setTipoIdentificacion(newUser.getTipoIdentificacion());
			user.setPassword(newUser.getPassword());
			user.setTelefono(newUser.getTelefono());
			registrarUsuarioRepository.save(user);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registro actualizado exitosamente");
			return response;
		}
		response.put(Constantes.CODIGO_HTTP, "500");
		response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al actualizar el registro");
		return response;

	}

}
