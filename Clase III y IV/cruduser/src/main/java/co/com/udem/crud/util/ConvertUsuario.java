package co.com.udem.crud.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;

import co.com.udem.crud.dto.UsuarioDTO;
import co.com.udem.crud.entities.Usuario;

public class ConvertUsuario {

	public ConvertUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	private ModelMapper modelMapper = new ModelMapper();
	   
    public Usuario convertToEntity(UsuarioDTO usuarioDTO) throws ParseException {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }
    public UsuarioDTO convertToDTO(Usuario usuario) throws ParseException {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
