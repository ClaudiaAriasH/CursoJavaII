package co.com.udem.agenciainmobiliaria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoIdentificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipoDocumento;
	private String descripcion;

	public TipoIdentificacion() {
		super();

	}

	public TipoIdentificacion(Long id, String tipoDocumento, String descripcion, RegistrarUsuario registrarUsuario) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
