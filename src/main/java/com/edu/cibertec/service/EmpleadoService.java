package com.edu.cibertec.service;

import java.io.IOException;
import java.util.List;

import com.edu.cibertec.model.Empleado;
import com.edu.cibertec.model.EmpleadoPOJO;
import com.edu.cibertec.model.EmpleadoReportePOJO;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

public interface EmpleadoService { // <----------------

	public List<Empleado> listar();
	public List<EmpleadoReportePOJO> listaReportePOJO();
	public void eliminar(Empleado empleado);
	public void eliminarByCodigo(String codigo);
	public Empleado guardar(EmpleadoPOJO empleadoPOJO);
	public Empleado getEmpleadoByCodigo(String codigo);
	public String getUltimoCodigo();
	public void exportarReporte(String tipo, HttpServletResponse response) throws JRException,IOException;
	
}
