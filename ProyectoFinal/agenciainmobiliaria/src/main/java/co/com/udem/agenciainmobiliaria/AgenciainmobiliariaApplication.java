package co.com.udem.agenciainmobiliaria;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.udem.agenciainmobiliaria.util.ConvertRegistrarUsuario;

@SpringBootApplication
public class AgenciainmobiliariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgenciainmobiliariaApplication.class, args);
	}

	@Bean
	public ConvertRegistrarUsuario convertRegistrarUsuario() {
		return new ConvertRegistrarUsuario();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}