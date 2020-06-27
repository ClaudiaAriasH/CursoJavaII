package co.com.udem.crudlibreria.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.crudlibreria.entities.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long> {

}
