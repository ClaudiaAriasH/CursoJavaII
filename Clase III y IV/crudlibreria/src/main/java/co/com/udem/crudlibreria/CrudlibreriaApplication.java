package co.com.udem.crudlibreria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAutoConfiguration
@ComponentScan(basePackages="co.com.udem.crudlibreria")
@EnableJpaRepositories(basePackages="co.com.udem.crudlibreria.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="co.com.udem.crudlibreria.entities")
@SpringBootApplication
public class CrudlibreriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudlibreriaApplication.class, args);
	}

}
