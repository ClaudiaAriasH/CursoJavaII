package co.com.udem.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.crud.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
