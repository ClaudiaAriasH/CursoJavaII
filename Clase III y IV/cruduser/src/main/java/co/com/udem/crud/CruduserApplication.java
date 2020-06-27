package co.com.udem.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//permite habilitar el contexto de spring
@EnableAutoConfiguration
//Busca para habiltar configuración y escaneo de clases que tenga componen, reposiories, entities, etc
@ComponentScan(basePackages="co.com.udem.crud")
//Busca crud 
@EnableJpaRepositories(basePackages="co.com.udem.crud.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="co.com.udem.crud.entities")
@SpringBootApplication
public class CruduserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruduserApplication.class, args);
	}

}
