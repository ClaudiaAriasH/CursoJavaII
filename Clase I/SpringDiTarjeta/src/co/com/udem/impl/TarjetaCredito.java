package co.com.udem.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.com.udem.interfaces.Tarjeta;


@Component
@Qualifier("tarjetaCreditoBean")
public class TarjetaCredito implements Tarjeta{

	@Override
	public void validarSaldo() {
		System.out.println("El saldo en su tarjeta credito es 20.000");
		
	}

	@Override
	public void efectuarRetiro() {
		System.out.println("Se realizo un retiro exitoso de su tarjeta credito de 10.000");
		
		
	}
	

}
