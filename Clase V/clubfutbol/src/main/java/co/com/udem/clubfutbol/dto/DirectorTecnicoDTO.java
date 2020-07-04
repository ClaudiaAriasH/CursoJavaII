package co.com.udem.clubfutbol.dto;

public class DirectorTecnicoDTO {

	private Long id;
	private String nombres;
	private String apellidos;
	private int edad;

	public DirectorTecnicoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DirectorTecnicoDTO(Long id, String nombres, String apellidos, int edad) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
