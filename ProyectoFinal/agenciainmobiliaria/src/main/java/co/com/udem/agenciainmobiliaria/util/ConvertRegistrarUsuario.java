package co.com.udem.agenciainmobiliaria.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.agenciainmobiliaria.dto.RegistrarUsuarioDTO;
import co.com.udem.agenciainmobiliaria.entities.RegistrarUsuario;

public class ConvertRegistrarUsuario {

	@Autowired
	private ModelMapper modelMapper;

	public RegistrarUsuario convertToEntity(RegistrarUsuarioDTO registrarUsuarioDTO) throws ParseException {
		return modelMapper.map(registrarUsuarioDTO, RegistrarUsuario.class);
	}

	public RegistrarUsuarioDTO convertToDTO(RegistrarUsuario registrarUsuario) throws ParseException {
		
		return modelMapper.map(registrarUsuario, RegistrarUsuarioDTO.class);
	}

	public List<RegistrarUsuarioDTO> convertToDTOIterable(Iterable<RegistrarUsuario> registrarUsuario)
			throws ParseException {

		List<RegistrarUsuario> usuario = new ArrayList<>();
		registrarUsuario.forEach(usuario::add);
		return modelMapper.map(usuario, new TypeToken<List<RegistrarUsuarioDTO>>() {
		}.getType());

	}
}
