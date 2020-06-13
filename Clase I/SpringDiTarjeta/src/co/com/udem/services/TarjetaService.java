package co.com.udem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.com.udem.interfaces.Tarjeta;

@Component
public class TarjetaService {

	@Autowired
	@Qualifier("tarjetaCreditoBean")
	private Tarjeta tarjeta;

	@Autowired
	@Qualifier("tarjetaDebitoBean")
	private Tarjeta tarjetaDebito;

	public void imprimirMensajeTarjeta() {
		System.out.println("******* Información tarjeta credito *******************");
		
		tarjeta.validarSaldo();
		tarjeta.efectuarRetiro();
		System.out.println("\n");
		System.out.println("******* Información tarjeta debito *******************");
		

		tarjetaDebito.validarSaldo();
		tarjetaDebito.efectuarRetiro();
	}
}
