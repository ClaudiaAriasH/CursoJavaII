package co.com.udem.dto;

public class Empleado {

	private String nombre;
	private String apellido;
	private Double salario;
	private String departamento;

	public Empleado(String nombre, String apellido, Double salario, String departamento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.salario = salario;
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", apellido=" + apellido + ", salario=" + salario + ", departamento="
				+ departamento + "]";
	}

}
