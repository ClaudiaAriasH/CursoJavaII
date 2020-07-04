package co.com.udem.clubfutbol.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class ClubFutbolDTO {

	private Long id;
	private String nombreEquipo;
	private String ciudadSede;
	private String fechaFundado;
	@Autowired
	private DirectorTecnicoDTO directorTecnicoDTO;

	public ClubFutbolDTO() {
		super();

	}

	public ClubFutbolDTO(Long id, String nombreEquipo, String ciudadSede, String fechaFundado,
			DirectorTecnicoDTO directorTecnicoDTO) {
		super();
		this.id = id;
		this.nombreEquipo = nombreEquipo;
		this.ciudadSede = ciudadSede;
		this.fechaFundado = fechaFundado;
		this.directorTecnicoDTO = directorTecnicoDTO;
	}

	public DirectorTecnicoDTO getDirectorTecnicoDTO() {
		return directorTecnicoDTO;
	}

	public void setDirectorTecnicoDTO(DirectorTecnicoDTO directorTecnicoDTO) {
		this.directorTecnicoDTO = directorTecnicoDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getCiudadSede() {
		return ciudadSede;
	}

	public void setCiudadSede(String ciudadSede) {
		this.ciudadSede = ciudadSede;
	}

	public String getFechaFundado() {
		return fechaFundado;
	}

	public void setFechaFundado(String fechaFundado) {
		this.fechaFundado = fechaFundado;
	}

}
