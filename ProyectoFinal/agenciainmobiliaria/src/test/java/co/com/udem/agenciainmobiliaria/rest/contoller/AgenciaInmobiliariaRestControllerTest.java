package co.com.udem.agenciainmobiliaria.rest.contoller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import co.com.udem.agenciainmobiliaria.AgenciainmobiliariaApplication;
import co.com.udem.agenciainmobiliaria.dto.RegistrarUsuarioDTO;
import co.com.udem.agenciainmobiliaria.entities.RegistrarUsuario;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AgenciainmobiliariaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgenciaInmobiliariaRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void adicionarClubFubolTest() {
		RegistrarUsuarioDTO registrarUsuarioDTO = new RegistrarUsuarioDTO();
		registrarUsuarioDTO.setApellidos("Sasha");
		registrarUsuarioDTO.setNombres("Saenz");
		registrarUsuarioDTO.setDireccion("Carrera 68B");
		registrarUsuarioDTO.setEmail("sash@gmail.com");
		registrarUsuarioDTO.setNumeroIdentificacion("1037645338");
		registrarUsuarioDTO.setTipoIdentificacion("PA");
		registrarUsuarioDTO.setPassword("2020sash*");
		registrarUsuarioDTO.setTelefono("5982764");
		ResponseEntity<RegistrarUsuarioDTO> postResponse = restTemplate.postForEntity(
				getRootUrl() + "/agenciaInmobiliaria/adicionarUsuario", registrarUsuarioDTO, RegistrarUsuarioDTO.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());

	}

	@Test
	public void testUpdateUsuario() {
		int id = 4;
		RegistrarUsuarioDTO registrarUsuarioDTO = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id,
				RegistrarUsuarioDTO.class);

		registrarUsuarioDTO.setPassword("1994sash**");
		registrarUsuarioDTO.setTelefono("5982211");
		restTemplate.put(getRootUrl() + "/usuarios/" + id, registrarUsuarioDTO);
		RegistrarUsuarioDTO updatedUsuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id,
				RegistrarUsuarioDTO.class);
		assertNotNull(updatedUsuario);
	}

	@Test
	public void testDeleteUsuario() {
		int id = 4;
		RegistrarUsuarioDTO usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id,
				RegistrarUsuarioDTO.class);
		assertNotNull(usuario);
		restTemplate.delete(getRootUrl() + "/usuarios/" + id);
		try {
			usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, RegistrarUsuarioDTO.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/usuarios", HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetUsuarioById() {
		RegistrarUsuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/1", RegistrarUsuario.class);
		System.err.println("Datos testGetUsuarioById: "+ usuario.getNumeroIdentificacion());
		assertNotNull(usuario);
	}
}
