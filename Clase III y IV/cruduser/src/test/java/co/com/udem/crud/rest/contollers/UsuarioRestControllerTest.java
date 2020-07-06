package co.com.udem.crud.rest.contollers;

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
import co.com.udem.crud.CruduserApplication;
import co.com.udem.crud.entities.Usuario;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CruduserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

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
		Usuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/1", Usuario.class);
		System.out.println(usuario.getFirsName());
		assertNotNull(usuario);
	}

	@Ignore
	public void testCreateUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("admin@gmail.com");
		usuario.setFirsName("admin");
		usuario.setLastName("admin");
		ResponseEntity<Usuario> postResponse = restTemplate.postForEntity(getRootUrl() + "/usuarios/adicionarUsuario",
				usuario, Usuario.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateUsuario() {
		int id = 4;
		Usuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
	
		usuario.setFirsName("admin1");
		usuario.setLastName("admin2");
		restTemplate.put(getRootUrl() + "/usuarios/" + id, usuario);
		Usuario updatedUsuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
		assertNotNull(updatedUsuario);
	}

	@Test
	public void testDeleteUsuario() {
		int id = 4;
		Usuario usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
		assertNotNull(usuario);
		restTemplate.delete(getRootUrl() + "/usuarios/" + id);
		try {
			usuario = restTemplate.getForObject(getRootUrl() + "/usuarios/" + id, Usuario.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
