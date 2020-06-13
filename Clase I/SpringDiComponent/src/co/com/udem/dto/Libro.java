package co.com.udem.dto;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;

@Component
public class Libro {

	@Autowired
	private Autor autor;

	private String titulo = "Aprendiendo Spring";
	private String genero = "Aventuras";
	private String editorial = "Una";
	private int edicion = 2;
	private int paginas = 257;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", genero=" + genero + ", editorial=" + editorial + ", autor=" + autor
				+ ", edicion=" + edicion + ", paginas=" + paginas + "]";
	}

}
