package co.com.udem.main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.com.udem.dto.Empresa;

public class Main {

	public static void main(String[] args) {

		BeanFactory factory = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		Empresa empresa = (Empresa) factory.getBean("empresa");
		
		System.out.println("*************************** Datos Empresa ********************************** " );
		System.out.println("Nit: " + empresa.getNit());
		System.out.println("Nombre Empresa: " + empresa.getNombre());
		System.out.println("Dirección Empresa: " + empresa.getDireccion());
		System.out.println("**************************  Datos Trabajador *********************************" );
		System.out.println("Nombre: " + empresa.getEmpleado().getNombre());
		System.out.println( "Apellido: " + empresa.getEmpleado().getApellido());
		System.out.println(	"Salario: "		+ empresa.getEmpleado().getSalario());
		System.out.println("Departamento: " + empresa.getEmpleado().getDepartamento());

	}

}
