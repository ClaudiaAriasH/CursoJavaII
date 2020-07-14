package co.com.udem.agenciainmobiliaria.rest.contoller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	private static final Logger logger = LogManager.getLogger(AgenciaInmobiliariaRestController.class);
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
			TipoIdentificacion tipoDocumentoExiste = tipoIdentificacionRepository
					.findByTipoDocumento(tipoIdentificacion.getTipoDocumento());

			if (tipoDocumentoExiste != null) {
				response.put(Constantes.CODIGO_HTTP, "100");
				response.put(Constantes.MENSAJE_EXITO, "El tipo de documento ya existe");
			} else {

				tipoIdentificacionRepository.save(tipoIdentificacion);
				response.put(Constantes.CODIGO_HTTP, "200");
				response.put(Constantes.MENSAJE_EXITO, "Se insertado el Tipo de Documento  exitosamente");
			}
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Fallo al adicionar el Tipo de Documento");
			return response;
		}

	}

	@GetMapping("/tiposDocumentos")
	public List<TipoIdentificacionDTO> listarTiposDocumentos() {

		List<TipoIdentificacionDTO> tipoDocumentosDTO = null;
		try {
			Iterable<TipoIdentificacion> tipoDocumentos = tipoIdentificacionRepository.findAll();
			tipoDocumentosDTO = convertTipoIdentificacion.convertToDTOIterable(tipoDocumentos);

		} catch (ParseException e) {
			logger.error("Error al convertir Tipo de documentos en DTO");
		}
		return tipoDocumentosDTO;
	}

	@DeleteMapping("/tiposDocumentos/{id}")
	public Map<String, String> eliminarTipoDocumento(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		if (tipoIdentificacionRepository.findById(id).isPresent()) {
			tipoIdentificacionRepository.deleteById(id);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_ERROR, "Registro eliminado de forma exitosa");
			return response;
		} else {
			response.put(Constantes.CODIGO_HTTP, "100");
			response.put(Constantes.MENSAJE_ERROR, "El registro no existe en la base de datos");
		}
		return response;
	}

	@GetMapping("/tiposDocumentos/{id}")
	public Map<String, Object> buscarTipoDocumento(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();

		try {
			if (tipoIdentificacionRepository.findById(id).isPresent()) {
				TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(id).get();

				tipoIdentificacionDTO = convertTipoIdentificacion.convertToDTO(tipoIdentificacion);
				response.put(Constantes.CODIGO_HTTP, "200");
				response.put("datos", tipoIdentificacionDTO);
				return response;
			}

			else {
				response.put(Constantes.CODIGO_HTTP, "100");
				response.put(Constantes.MENSAJE_EXITO, "El tipo de documento no existe");
			}
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Fallo al buscar el Tipo de Documento");
		}
		return response;
	}

	@PutMapping("/tiposDocumentos/{id}")
	public Map<String, String> updateTipoDocumento(@RequestBody TipoIdentificacionDTO newTipoDocDTO,
			@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		try {
			if (tipoIdentificacionRepository.findById(id).isPresent()) {

				TipoIdentificacion newTipoDoc = convertTipoIdentificacion.convertToEntity(newTipoDocDTO);

				TipoIdentificacion tipoDoc = tipoIdentificacionRepository.findById(id).get();

				tipoDoc.setTipoDocumento(newTipoDoc.getTipoDocumento());
				tipoDoc.setDescripcion(newTipoDoc.getDescripcion());
				tipoIdentificacionRepository.save(tipoDoc);
				response.put(Constantes.CODIGO_HTTP, "200");
				response.put(Constantes.MENSAJE_EXITO, "Se actualizo el Tipo de Documento exitosamente");
				return response;
			} else {
				response.put(Constantes.CODIGO_HTTP, "100");
				response.put(Constantes.MENSAJE_EXITO, "El tipo de documento no existe");

			}

		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Fallo al realizar la actualización del Tipo de Documento");
			return response;

		}
		return response;

	}

	@PostMapping("/agenciaInmobiliaria/adicionarUsuario")
	public Map<String, String> adicionarUsuario(@RequestBody RegistrarUsuarioDTO registrarUsuarioDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			RegistrarUsuario registrarUsuario = convertRegistrarUsuario.convertToEntity(registrarUsuarioDTO);

			RegistrarUsuario usuarioExiste = registrarUsuarioRepository.findByNumeroIdentificacionAndTipoIdentificacion(
					registrarUsuario.getNumeroIdentificacion(), registrarUsuario.getTipoIdentificacion());

			if (usuarioExiste != null) {
				response.put(Constantes.CODIGO_HTTP, "100");
				response.put(Constantes.MENSAJE_EXITO, "El usuario ya existe");
			} else {
				registrarUsuarioRepository.save(registrarUsuario);
				response.put(Constantes.CODIGO_HTTP, "200");
				response.put(Constantes.MENSAJE_EXITO, "Registrado insertado exitosamente");
			}
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar");
			return response;
		}

	}

	@GetMapping("/usuarios")
	public List<RegistrarUsuarioDTO> listarUsuarios() {

		List<RegistrarUsuarioDTO> registrarUsuarioDTO = new ArrayList<>();
		try {
			Iterable<RegistrarUsuario> registrarUsuario = registrarUsuarioRepository.findAll();
			registrarUsuarioDTO = convertRegistrarUsuario.convertToDTOIterable(registrarUsuario);

		} catch (ParseException e) {
			logger.error("Error al convertir Registrar Usuario en DTO");
		}
		return registrarUsuarioDTO;
	}

	@DeleteMapping("/usuarios/{id}")
	public Map<String, String> eliminarUsuario(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		if (registrarUsuarioRepository.findById(id).isPresent()) {
			registrarUsuarioRepository.deleteById(id);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_ERROR, "Registro eliminado de forma exitosa");
			return response;
		} else {
			response.put(Constantes.CODIGO_HTTP, "100");
			response.put(Constantes.MENSAJE_ERROR, "El registro no existe en la base de datos");
		}

		return response;

	}

	@GetMapping("/usuarios/{id}")
	public Map<String, Object> buscarUsuario(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		RegistrarUsuarioDTO registrarUsuarioDTO = new RegistrarUsuarioDTO();

		try {

			if (registrarUsuarioRepository.findById(id).isPresent()) {
				RegistrarUsuario usuario = registrarUsuarioRepository.findById(id).get();

				registrarUsuarioDTO = convertRegistrarUsuario.convertToDTO(usuario);
				response.put(Constantes.CODIGO_HTTP, "200");
				response.put("datos", registrarUsuarioDTO);
				return response;
			} else {
				response.put(Constantes.CODIGO_HTTP, "100");
				response.put(Constantes.MENSAJE_EXITO, "El usuario no existe");
			}

		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Fallo al buscar el Usuario");
		}
		return response;
	}

	@PutMapping("/usuarios/{id}")
	public Map<String, String> updateUser(@RequestBody RegistrarUsuarioDTO newUserDTO, @PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		try {

			if (registrarUsuarioRepository.findById(id).isPresent()) {
				RegistrarUsuario newUser = convertRegistrarUsuario.convertToEntity(newUserDTO);
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
				response.put(Constantes.MENSAJE_EXITO, "Se actualizo el Usuario exitosamente");
				return response;
			} else {
				response.put(Constantes.CODIGO_HTTP, "100");
				response.put(Constantes.MENSAJE_EXITO, "El usuario no existe");

			}

		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Fallo al realizar la actualización del Usuario");
			return response;

		}
		return response;

	}

}
