package co.com.udem.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class Empresa {

	@Autowired
	private Empleado empleado;

	private String nit = "89090082-4";
	private String nombre = "Comapany LJ";
	private String direccion = "calle 56 A # 45- 3";
	private int telefono = 5982253;

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Empresa [empleado=" + empleado + ", nit=" + nit + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", telefono=" + telefono + "]";
	}

}
