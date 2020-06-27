package co.com.udem.crudlibreria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO  )
	private Long id;
	private String nombreLibro;
	private String nombreEditorial;
	private int anioEdicion;

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libro(Long id, String nombreLibro, String nombreEditorial, int anioEdicion) {
		super();
		this.id = id;
		this.nombreLibro = nombreLibro;
		this.nombreEditorial = nombreEditorial;
		this.anioEdicion = anioEdicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public String getNombreEditorial() {
		return nombreEditorial;
	}

	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}

	public int getAnioEdicion() {
		return anioEdicion;
	}

	public void setAnioEdicion(int anioEdicion) {
		this.anioEdicion = anioEdicion;
	}

}
