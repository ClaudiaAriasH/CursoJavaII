package co.com.udem.clubfutbol.rest.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.clubfutbol.dto.ClubFutbolDTO;
import co.com.udem.clubfutbol.dto.entities.ClubFutbol;
import co.com.udem.clubfutbol.repositories.ClubFutbolRepository;
import co.com.udem.clubfutbol.util.Constantes;
import co.com.udem.clubfutbol.util.ConvertClubFutbol;


@RestController
public class ClubFutbolRestController {

	@Autowired
	private ClubFutbolRepository clubFutbolRepository;
	@Autowired
	private ConvertClubFutbol convertClubFutbol;

	@PostMapping("/clubes/adicionarClub")
	public Map<String, String> adicionarLibro(@RequestBody ClubFutbolDTO clubFutbolDTO) {
		Map<String, String> response = new HashMap<>();
		try {
			ClubFutbol clubFutbol = convertClubFutbol.convertToEntity(clubFutbolDTO);
			clubFutbolRepository.save(clubFutbol);
			response.put(Constantes.CODIGO_HTTP, "200");
			response.put(Constantes.MENSAJE_EXITO, "Registrado insertado exitosamente");
			return response;
		} catch (ParseException e) {
			response.put(Constantes.CODIGO_HTTP, "500");
			response.put(Constantes.MENSAJE_ERROR, "Ocurrió un problema al insertar");
			return response;
		}

	}

}
