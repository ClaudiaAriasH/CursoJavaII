package co.com.udem.clubfutbol.dto.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ClubFutbol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombreEquipo;
	private String ciudadSede;
	private String fechaFundado;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "dt_id", referencedColumnName = "id")
	private DirectorTecnico directorTecnico;

	public ClubFutbol() {
		super();
	
	}


	public ClubFutbol(Long id, String nombreEquipo, String ciudadSede, String fechaFundado,
			DirectorTecnico directorTecnico) {
		super();
		this.id = id;
		this.nombreEquipo = nombreEquipo;
		this.ciudadSede = ciudadSede;
		this.fechaFundado = fechaFundado;
		this.directorTecnico = directorTecnico;
	}
	
	


	public DirectorTecnico getDirectorTecnico() {
		return directorTecnico;
	}


	public void setDirectorTecnico(DirectorTecnico directorTecnico) {
		this.directorTecnico = directorTecnico;
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
