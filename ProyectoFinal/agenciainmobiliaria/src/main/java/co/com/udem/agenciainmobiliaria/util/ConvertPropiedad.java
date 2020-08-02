package co.com.udem.agenciainmobiliaria.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.agenciainmobiliaria.dto.PropiedadDTO;
import co.com.udem.agenciainmobiliaria.entities.Propiedad;

public class ConvertPropiedad {

	@Autowired
	private ModelMapper modelMapper;

	public Propiedad convertToEntity(PropiedadDTO propiedadDTO) throws ParseException {
		return modelMapper.map(propiedadDTO, Propiedad.class);
	}

	public PropiedadDTO convertToDTO(Propiedad propiedad) throws ParseException {
		return modelMapper.map(propiedad, PropiedadDTO.class);
	}

	public List<PropiedadDTO> convertToDTOIterable(Iterable<Propiedad> propiedad) throws ParseException {

		List<Propiedad> propiedadList = new ArrayList<>();
		propiedad.forEach(propiedadList::add);
		return modelMapper.map(propiedadList, new TypeToken<List<PropiedadDTO>>() {
		}.getType());

	}

}
