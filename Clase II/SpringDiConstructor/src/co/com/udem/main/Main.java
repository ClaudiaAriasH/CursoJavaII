package co.com.udem.main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.com.udem.dto.Libro;

public class Main {

	public static void main(String[] args) {
		
		BeanFactory factory = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		Libro libro = (Libro) factory.getBean("libro");
		System.out.println("- " + libro.getTitulo());
		System.out.println("- " + libro.getAutor().getNombre() + " " + libro.getAutor().getApellido());
		System.out.println("- " + libro.getEditorial());
		System.out.println("- " + libro.getGenero());
		System.out.println("- " + libro.getEdicion());
		System.out.println("- " + libro.getPaginas());

	}

}
