package co.com.udem.clubfutbol.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.udem.clubfutbol.dto.DirectorTecnicoDTO;
import co.com.udem.clubfutbol.dto.entities.DirectorTecnico;

public class ConvertDirectorTecnico {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public DirectorTecnico convertToEntity(DirectorTecnicoDTO directorTecnicoDTO) throws ParseException {
        return modelMapper.map(directorTecnicoDTO, DirectorTecnico.class);
    }
    
    public DirectorTecnicoDTO convertToDTO(DirectorTecnico directorTecnico) throws ParseException {
        return modelMapper.map(directorTecnico, DirectorTecnicoDTO.class);
    }

 

}
