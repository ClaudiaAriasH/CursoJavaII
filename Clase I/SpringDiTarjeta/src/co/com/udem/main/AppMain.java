package co.com.udem.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.com.udem.services.TarjetaService;

public class AppMain {

	public static void main(String[] args) {
		
		//Con esto nos decisimos del context xml
		AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext();



          // Scan beans
          context.scan("co.com.udem.impl");
          context.scan("co.com.udem.services");
          context.refresh();



          TarjetaService tarjeta = context.getBean(TarjetaService.class);
          tarjeta.imprimirMensajeTarjeta();



          context.close();
	}

}
