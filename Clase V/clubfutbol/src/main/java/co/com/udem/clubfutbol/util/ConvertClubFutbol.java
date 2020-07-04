package co.com.udem.clubfutbol.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.clubfutbol.dto.ClubFutbolDTO;
import co.com.udem.clubfutbol.dto.entities.ClubFutbol;

public class ConvertClubFutbol {

	@Autowired
	private ModelMapper modelMapper;

	public ClubFutbol convertToEntity(ClubFutbolDTO clubFutbolDTO) throws ParseException {
		return modelMapper.map(clubFutbolDTO, ClubFutbol.class);
	}

	public ClubFutbolDTO convertToDTO(ClubFutbol clubFutbol) throws ParseException {
		return modelMapper.map(clubFutbol, ClubFutbolDTO.class);
	}
}
