package co.com.udem.agenciainmobiliaria.rest.contoller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import co.com.udem.agenciainmobiliaria.AgenciainmobiliariaApplication;
import co.com.udem.agenciainmobiliaria.dto.AutenticationRequestDTO;
import co.com.udem.agenciainmobiliaria.dto.AutenticationResponseDTO;
import co.com.udem.agenciainmobiliaria.dto.PropiedadDTO;
import co.com.udem.agenciainmobiliaria.dto.RegistrarUsuarioDTO;
import co.com.udem.agenciainmobiliaria.dto.TipoIdentificacionDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AgenciainmobiliariaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgenciaInmobiliariaRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String token;

	private AutenticationRequestDTO autenticationRequestDTO = new AutenticationRequestDTO();

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Before
	public void authorization() {
		
		
		autenticationRequestDTO.setUsername("10376410302");
		autenticationRequestDTO.setPassword("pruebaToken");
		adicionarUsuarioWebTest(autenticationRequestDTO);
		ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/auth/signin",
				autenticationRequestDTO, String.class);
		Gson g = new Gson();
		AutenticationResponseDTO autenticationResponseDTO = g.fromJson(postResponse.getBody(),
				AutenticationResponseDTO.class);
		token = autenticationResponseDTO.getToken();
	}

//	private void adicionarUsuario(AutenticationRequestDTO autenticationRequestDTO) {
//		ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/users/addUser",
//				autenticationRequestDTO, String.class);
//		postResponse.getBody();
//	}
//
//	@Test
	public void adicionarUsuarioWebTest(AutenticationRequestDTO autenticationRequestDTO) {
		RegistrarUsuarioDTO registrarUsuarioDTO = new RegistrarUsuarioDTO();
		registrarUsuarioDTO.setApellidos("Arias Hernandez");
		registrarUsuarioDTO.setNombres("Claudia ");
		registrarUsuarioDTO.setDireccion("Carrera 60 # 59-38");
		registrarUsuarioDTO.setEmail("claarher@gmail.com");
		registrarUsuarioDTO.setNumeroIdentificacion(autenticationRequestDTO.getUsername());
		registrarUsuarioDTO.setPassword(autenticationRequestDTO.getPassword());
		registrarUsuarioDTO.setTelefono("5982252");
		TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
		tipoIdentificacionDTO.setId(6L);
		tipoIdentificacionDTO.setTipoDocumento("CC");
		tipoIdentificacionDTO.setDescripcion("Cédula de Ciudadanía");
		registrarUsuarioDTO.setTipoIdentificacionDTO(tipoIdentificacionDTO);
		ResponseEntity<RegistrarUsuarioDTO> postResponse = restTemplate.postForEntity(
				getRootUrl() + "/agenciaInmobiliaria/adicionarUsuario", registrarUsuarioDTO, RegistrarUsuarioDTO.class);
		postResponse.getBody();

		assertEquals(200, postResponse.getStatusCode().value());

	}

	@Test
	public void testUpdateUsuario() {
		int id = 17;
		RegistrarUsuarioDTO registrarUsuarioDTO = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id,
				RegistrarUsuarioDTO.class);

		registrarUsuarioDTO.setApellidos("Saens Prueba");
		registrarUsuarioDTO.setNombres("Sasha");
		registrarUsuarioDTO.setDireccion("Carrera 68B");
		registrarUsuarioDTO.setEmail("sash01@gmail.com");
		registrarUsuarioDTO.setNumeroIdentificacion("111111122");
		registrarUsuarioDTO.setPassword("1990sash**");
		registrarUsuarioDTO.setTelefono("5980099");
		TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
		tipoIdentificacionDTO.setId(6L);
		registrarUsuarioDTO.setTipoIdentificacionDTO(tipoIdentificacionDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testUpdateUsuario es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<RegistrarUsuarioDTO> entity = new HttpEntity<RegistrarUsuarioDTO>(registrarUsuarioDTO, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/usuarios/" + id, HttpMethod.PUT,
				entity, String.class);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testDeleteUsuario() {
		int id = 17;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testDeleteUsuario es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/usuarios/" + id, HttpMethod.DELETE,
				entity, String.class);
		System.out.println("Datos testDeleteUsuario: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testGetAllUsers() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testGetAllUsers es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/usuarios", HttpMethod.GET, entity,
				String.class);
		System.out.println("Datos testGetAllUsers: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testGetUsuarioById() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testGetUsuarioById es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/usuarios/19", HttpMethod.GET,
				entity, String.class);
		System.out.println("Datos testGetUsuarioById: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void adicionarTipoDocumentoTest() {
		TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
		tipoIdentificacionDTO.setTipoDocumento("PE");
		tipoIdentificacionDTO.setDescripcion("Permiso Especial");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token adicionarTipoDocumentoTest es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<TipoIdentificacionDTO> entity = new HttpEntity<TipoIdentificacionDTO>(tipoIdentificacionDTO,
				headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(
				getRootUrl() + "/agenciaInmobiliaria/adicionarTipoDocumento", HttpMethod.POST, entity, String.class);

		assertEquals(200, postResponse.getStatusCode().value());

	}

	@Test
	public void updateTipoDocumentoTest() {
		int id = 2;
		TipoIdentificacionDTO tipoIdentificacionDTO = restTemplate.getForObject(getRootUrl() + "/tiposDocumentos/" + id,
				TipoIdentificacionDTO.class);

		tipoIdentificacionDTO.setTipoDocumento("CC");
		tipoIdentificacionDTO.setDescripcion("Cédula");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token updateTipoDocumentoTest es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<TipoIdentificacionDTO> entity = new HttpEntity<TipoIdentificacionDTO>(tipoIdentificacionDTO,
				headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/tiposDocumentos/" + id,
				HttpMethod.PUT, entity, String.class);

		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testDeleteTipoDocumento() {
		int id = 5;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testDeleteTipoDocumento es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/tiposDocumentos/" + id,
				HttpMethod.DELETE, entity, String.class);
		System.out.println("Datos testDeleteTipoDocumento: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testGetAllTiposDocumentos() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testGetAllTiposDocumentos es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/tiposDocumentos", HttpMethod.GET,
				entity, String.class);
		System.out.println("Datos testGetAllTiposDocumentos: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testGetTipoDocumentoById() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testGetTipoDocumentoById es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/tiposDocumentos/3", HttpMethod.GET,
				entity, String.class);
		System.out.println("Datos testGetTipoDocumentoById: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void adicionarPropiedadTest() {
		PropiedadDTO propiedadDTO = new PropiedadDTO();
		propiedadDTO.setArea("50m2");
		propiedadDTO.setNumeroBanos(2);
		propiedadDTO.setNumeroHabitaciones(3);
		propiedadDTO.setTipoPropiedad("Arriendo");
		propiedadDTO.setValor(100000);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token adicionarPropiedadTest es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<PropiedadDTO> entity = new HttpEntity<PropiedadDTO>(propiedadDTO, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(
				getRootUrl() + "/agenciaInmobiliaria/adicionarPropiedad", HttpMethod.POST, entity, String.class);

		assertEquals(200, postResponse.getStatusCode().value());

	}

	@Test
	public void updatePropiedadTest() {
		int id = 1;
		PropiedadDTO propiedadDTO = restTemplate.getForObject(getRootUrl() + "/propiedad/" + id, PropiedadDTO.class);

		propiedadDTO.setArea("50m2");
		propiedadDTO.setNumeroBanos(2);
		propiedadDTO.setNumeroHabitaciones(2);
		propiedadDTO.setTipoPropiedad("Arriendo");
		propiedadDTO.setValor(100000);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token updatePropiedadTest es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<PropiedadDTO> entity = new HttpEntity<PropiedadDTO>(propiedadDTO, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/propiedad/" + id, HttpMethod.PUT,
				entity, String.class);

		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testDeletePropiedad() {
		int id = 5;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testDeletePropiedad es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/propiedad/" + id,
				HttpMethod.DELETE, entity, String.class);
		System.out.println("Datos testDeletePropiedad: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	@Test
	public void testGetAllPropiedad() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testGetAllPropiedad es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/propiedades", HttpMethod.GET,
				entity, String.class);
		System.out.println("Datos testGetAllPropiedad: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}

	
	@Test
	public void testGetPropiedadById() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("El token testGetPropiedadById es: " + this.token);
		headers.set("Authorization", "Bearer " + this.token);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/propiedad/3", HttpMethod.GET,
				entity, String.class);
		System.out.println("Datos testGetPropiedadById: " + postResponse);
		assertEquals(200, postResponse.getStatusCode().value());
	}
}
