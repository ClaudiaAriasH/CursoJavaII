package co.com.udem.agenciainmobiliaria.filtros;

import org.springframework.data.jpa.domain.Specification;

import co.com.udem.agenciainmobiliaria.entities.Propiedad;

public class FilterSpecification {
	private FilterSpecification() {
	}

	public static Specification<Propiedad> withFilter(Object object, String columName) {
		return object == null ? null : (root, query, cb) -> cb.equal(root.get(columName), object);
	}
}
