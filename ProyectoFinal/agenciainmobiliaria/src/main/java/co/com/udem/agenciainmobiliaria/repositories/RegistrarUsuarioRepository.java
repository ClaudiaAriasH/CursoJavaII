package co.com.udem.agenciainmobiliaria.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.udem.agenciainmobiliaria.entities.RegistrarUsuario;

public interface RegistrarUsuarioRepository extends CrudRepository<RegistrarUsuario, Long> {

	@Query("SELECT u FROM RegistrarUsuario u WHERE u.numeroIdentificacion = ?1  and tipo_iden=?2")
	RegistrarUsuario buscarDocumentoTipo(String numeroIdentificacion, Long tipoIdentificacion);

}
