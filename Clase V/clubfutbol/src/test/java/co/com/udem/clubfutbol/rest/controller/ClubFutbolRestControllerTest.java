package co.com.udem.clubfutbol.rest.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.udem.clubfutbol.ClubfutbolApplication;
import co.com.udem.clubfutbol.dto.ClubFutbolDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClubfutbolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClubFutbolRestControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate; //Para probar servicios rest

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
    @Test
    public void adicionarClubFubolTest()
    {
    	ClubFutbolDTO clubFutbolDTO= new ClubFutbolDTO();
    	clubFutbolDTO.setNombreEquipo("Deportivo Cali");
    	clubFutbolDTO.setCiudadSede("Cali");
    	clubFutbolDTO.setFechaFundado("2018");
    	ResponseEntity<ClubFutbolDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/clubes/adicionarClub", clubFutbolDTO, ClubFutbolDTO.class);
        assertNotNull(postResponse); //valida que la respuesta no sea nula
        assertNotNull(postResponse.getBody());
    	
    }

}
