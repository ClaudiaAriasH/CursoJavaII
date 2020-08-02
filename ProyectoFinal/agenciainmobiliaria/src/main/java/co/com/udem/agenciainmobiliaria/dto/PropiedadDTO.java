package co.com.udem.agenciainmobiliaria.dto;

public class PropiedadDTO {

	private Long id;
	private String area;
	private int numeroHabitaciones;
	private int numeroBanos;
	private String tipoPropiedad;
	private double valor;

	public PropiedadDTO() {
		super();

	}

	public PropiedadDTO(Long id, String area, int numeroHabitaciones, int numeroBanos, String tipoPropiedad,
			double valor) {
		super();
		this.id = id;
		this.area = area;
		this.numeroHabitaciones = numeroHabitaciones;
		this.numeroBanos = numeroBanos;
		this.tipoPropiedad = tipoPropiedad;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public int getNumeroBanos() {
		return numeroBanos;
	}

	public void setNumeroBanos(int numeroBanos) {
		this.numeroBanos = numeroBanos;
	}

	public String getTipoPropiedad() {
		return tipoPropiedad;
	}

	public void setTipoPropiedad(String tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
