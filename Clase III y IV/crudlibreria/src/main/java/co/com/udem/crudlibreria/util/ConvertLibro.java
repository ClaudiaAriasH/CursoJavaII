package co.com.udem.crudlibreria.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;

import co.com.udem.crudlibreria.dto.LibroDTO;
import co.com.udem.crudlibreria.entities.Libro;

public class ConvertLibro {

	public ConvertLibro() {
		super();
		// TODO Auto-generated constructor stub
	}

	private ModelMapper modelMapper = new ModelMapper();

	public Libro convertToEntity(LibroDTO libroDTO) throws ParseException {
		return modelMapper.map(libroDTO, Libro.class);
	}
}
