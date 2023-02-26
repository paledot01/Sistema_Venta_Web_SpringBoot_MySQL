package com.edu.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cibertec.model.Distrito;
import com.edu.cibertec.repository.DistritoRepository;
import com.edu.cibertec.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService {
	
	
	@Autowired
	private DistritoRepository repositorio;
	
	// --> METODOS
	@Override
	public List<Distrito> listar() {
		return repositorio.findAll();
	}

	
}
