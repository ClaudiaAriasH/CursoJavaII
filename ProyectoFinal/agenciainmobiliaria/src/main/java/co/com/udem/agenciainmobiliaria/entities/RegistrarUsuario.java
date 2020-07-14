package co.com.udem.agenciainmobiliaria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegistrarUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombres;
	private String apellidos;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String direccion;
	private String telefono;
	private String email;
	private String password;

	public RegistrarUsuario() {
		super();

	}

	public RegistrarUsuario(Long id, String nombres, String apellidos, String tipoIdentificacion,
			String numeroIdentificacion, String direccion, String telefono, String email, String password) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipoIdentificacion = tipoIdentificacion;
		this.numeroIdentificacion = numeroIdentificacion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
