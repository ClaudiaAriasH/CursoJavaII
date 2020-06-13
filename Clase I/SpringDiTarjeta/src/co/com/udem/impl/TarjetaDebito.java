package co.com.udem.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.com.udem.interfaces.Tarjeta;

@Component
@Qualifier("tarjetaDebitoBean")
public class TarjetaDebito implements Tarjeta {

	@Override
	public void validarSaldo() {
		System.out.println("El saldo en su tarjeta debito es 20.000");
		
	}

	@Override
	public void efectuarRetiro() {
		System.out.println("Se realizo un retiro exitoso de su tarjeta debito de 10.000");
		
		
	}

	

}
