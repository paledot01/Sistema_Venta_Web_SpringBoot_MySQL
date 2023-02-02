package com.edu.cibertec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edu.cibertec.model.EmpleadoPOJO;
import com.edu.cibertec.service.EmpleadoService;


@SpringBootApplication
public class ShoesformenSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShoesformenSpringApplication.class, args);	
	}

}

// Esta interfaz realiza cualquier accion inmediatamente despues de que se haya iniciado la aplicacion.

/*
@SpringBootApplication
public class ShoesformenSpringApplication implements CommandLineRunner{

	@Autowired
	private EmpleadoService empleadoServ;
	
	public static void main(String[] args) {
		SpringApplication.run(ShoesformenSpringApplication.class, args);	
	}
	
	@Override
	public void run(String... args) throws Exception {
		EmpleadoPOJO emp = new EmpleadoPOJO("EM10028", "DI01", "1", "Pepe", "Flores", "45567897", "ddddd", "222233334", "pepe@gmail.com", "pepe", "123");
		empleadoServ.guardar(emp);
		System.out.println(emp);
	}

}*/

