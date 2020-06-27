package co.com.udem.crud.dto;

public class UsuarioDTO {

	private Long id;
	private String firsName;
	private String lastName;
	private String email;

	public UsuarioDTO() {
		super();

	}

	public UsuarioDTO(Long id, String firsName, String lastName, String email) {
		super();
		this.id = id;
		this.firsName = firsName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
