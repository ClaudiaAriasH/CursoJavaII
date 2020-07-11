package co.com.udem.agenciainmobiliaria.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.agenciainmobiliaria.entities.RegistrarUsuario;

public interface RegistrarUsuarioRepository extends CrudRepository<RegistrarUsuario, Long> {

	RegistrarUsuario findByNumeroIdentificacionAndTipoIdentificacion(String numeroIdentificacion,
			String tipoIdentificacion);
}
