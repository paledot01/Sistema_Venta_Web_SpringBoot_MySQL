package com.edu.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edu.cibertec.service.DistritoService;

@Controller
public class DistritoController {

	
	@Autowired
	private DistritoService servicio;
	
//	@GetMapping("/distrito") // la direccion
//	public String index(Model modelo) { // nombre del metodo
//		modelo.addAttribute("listaDistrito", servicio.listar()); // clave(este ira hasta la vista) , valor
//		return "index"; // el nombre de la vista
//	}
	
}
