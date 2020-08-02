package co.com.udem.clubfutbol.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import co.com.udem.clubfutbol.ClubfutbolApplication;
import co.com.udem.clubfutbol.dto.AutenticationRequestDTO;
import co.com.udem.clubfutbol.dto.AutenticationResponseDTO;
import co.com.udem.clubfutbol.dto.ClubFutbolDTO;
import co.com.udem.clubfutbol.dto.DirectorTecnicoDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClubfutbolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClubFutbolRestControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate; //Para probar servicios rest

    @LocalServerPort
    private int port;
    
    private String token;
    
    private AutenticationRequestDTO autenticationRequestDTO = new AutenticationRequestDTO();


    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
    @Before
    public void authorization() {
        autenticationRequestDTO.setUsername("julio");
        autenticationRequestDTO.setPassword("julio123**");
        adicionarUsuario(autenticationRequestDTO);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/auth/signin", autenticationRequestDTO, String.class);
        Gson g = new Gson();
        AutenticationResponseDTO autenticationResponseDTO = g.fromJson(postResponse.getBody(), AutenticationResponseDTO.class);
        token = autenticationResponseDTO.getToken();
    }
    
    @Test
    public void adicionarClubFubolTest()
    {
    	ClubFutbolDTO clubFutbolDTO = new ClubFutbolDTO();
        DirectorTecnicoDTO directorTecnicoDTO = new DirectorTecnicoDTO();
        clubFutbolDTO.setNombreEquipo("Deportivo Tapitas");
        clubFutbolDTO.setCiudadSede("Tangamandapio");
        clubFutbolDTO.setFechaFundado("2018");
        directorTecnicoDTO.setNombres("Pepito");
        directorTecnicoDTO.setApellidos("Perez");
        directorTecnicoDTO.setEdad(20);
        clubFutbolDTO.setDirectorTecnicoDTO(directorTecnicoDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("El token es: "+this.token);
        headers.set("Authorization", "Bearer "+this.token);
        HttpEntity<ClubFutbolDTO> entity = new HttpEntity<ClubFutbolDTO>(clubFutbolDTO, headers);
        ResponseEntity<String> postResponse = restTemplate.exchange(getRootUrl() + "/clubes/adicionarClub", HttpMethod.POST, entity, String.class);       
        //ResponseEntity<ClubFutbolDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/clubes/adicionarClub", clubFutbolDTO, ClubFutbolDTO.class);
        assertEquals(200, postResponse.getStatusCode().value());
        //assertNotNull(postResponse);
        //assertNotNull(postResponse.getBody());
    	
    }
    
    private void adicionarUsuario(AutenticationRequestDTO autenticationRequestDTO) {
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/users/addUser", autenticationRequestDTO, String.class);
        postResponse.getBody();
    }

}