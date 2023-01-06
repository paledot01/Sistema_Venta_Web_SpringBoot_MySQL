package com.edu.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.edu.cibertec.model.Estado;
import com.edu.cibertec.repository.EstadoRepository;
import com.edu.cibertec.service.EstadoService;

public class EstadoServiceImpl implements EstadoService{

	@Autowired
	private EstadoRepository repositorio;

	
	// --> Metodos
	@Override
	public List<Estado> listar() {
		return repositorio.findAll();
	}
	
}
