package com.edu.cibertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ShoesformenSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShoesformenSpringApplication.class, args);	
	}

}


// Esta interfaz realiza cualquier accion inmediatamente despues de que se haya iniciado la aplicacion.

//@SpringBootApplication
//public class ShoesformenSpringApplication implements CommandLineRunner{
//
//	@Autowired
//	private EmpleadoService empleadoServ;
//	
//	public static void main(String[] args) {
//		SpringApplication.run(ShoesformenSpringApplication.class, args);	
//	}
//	
//	@Override
//	public void run(String... args) throws Exception {
//		EmpleadoPOJO emp = new EmpleadoPOJO("EM10028", "DI01", "1", "pepe", "Flores", "45567897", "ddddd", "222233334", "pepe@gmail.com", "pepe", "123");
//		EmpleadoPOJO emp2 = new EmpleadoPOJO("EM10029", "DI01", "1", "jesus", "nasaret", "44445555", "aaaaaa", "122233334", "jesus@gmail.com", "jesus", "admin");
//		empleadoServ.guardar(emp);
//		empleadoServ.guardar(emp2);
//		System.out.println(emp);
//		System.out.println(emp2);
//	}
//
//}

