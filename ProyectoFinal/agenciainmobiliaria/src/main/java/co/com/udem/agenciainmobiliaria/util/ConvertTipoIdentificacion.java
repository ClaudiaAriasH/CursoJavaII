package co.com.udem.agenciainmobiliaria.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.agenciainmobiliaria.dto.TipoIdentificacionDTO;
import co.com.udem.agenciainmobiliaria.entities.TipoIdentificacion;

public class ConvertTipoIdentificacion {

	@Autowired
	private ModelMapper modelMapper;

	public TipoIdentificacion convertToEntity(TipoIdentificacionDTO tipoIdentificacionDTO) throws ParseException {
		return modelMapper.map(tipoIdentificacionDTO, TipoIdentificacion.class);
	}

	public TipoIdentificacionDTO convertToDTO(TipoIdentificacion tipoIdentificacion) throws ParseException {
		return modelMapper.map(tipoIdentificacion, TipoIdentificacionDTO.class);
	}
}
