package co.com.udem.clubfutbol.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.clubfutbol.dto.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
