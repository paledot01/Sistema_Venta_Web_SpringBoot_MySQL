package com.edu.cibertec.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.cibertec.model.Distrito;
import com.edu.cibertec.model.Empleado;
import com.edu.cibertec.model.EmpleadoPOJO;
import com.edu.cibertec.model.EmpleadoReportePOJO;
import com.edu.cibertec.model.Estado;
import com.edu.cibertec.model.Rol;
import com.edu.cibertec.repository.DistritoRepository;
import com.edu.cibertec.repository.EmpleadoRepository;
import com.edu.cibertec.repository.EstadoRepository;
import com.edu.cibertec.repository.RolRepository;
import com.edu.cibertec.service.EmpleadoService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoRepository empleadoRepo;
	
//	@Autowired
//	private RolRepository rolRepo;
	
	@Autowired
	private DistritoRepository distritoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private RolRepository rolRepo;

	@Autowired
	private PasswordEncoder encriptador;
	
	
	
	// -- METODOS
	@Override
	public List<Empleado> listar() {
		return empleadoRepo.findAll();
	}
	
	@Override
	public List<EmpleadoReportePOJO> listaReportePOJO() {
		List<EmpleadoReportePOJO> lista = empleadoRepo.listaPOJO();
		return lista;
	}
	
	@Override
	public void eliminar(Empleado empleado) {
		empleadoRepo.delete(empleado);
	}
	
	@Override
	public void eliminarByCodigo(String codigo) {
		empleadoRepo.deleteById(codigo);
	}

	@Override
	public Empleado guardar(EmpleadoPOJO empleadoPOJO) { // Obtenemos un Empleado a partir de un EmpleadoDTO
		
		Optional<Rol> optionalRol= rolRepo.findById("RL02"); // USER
		Rol objRol= null;
		if(optionalRol.isPresent())
			objRol = optionalRol.get();
		
		List<Rol> roles = Arrays.asList(objRol);
		
		Optional<Distrito> optionalDistrito= distritoRepo.findById(empleadoPOJO.getCod_distrito());
		Distrito objDistrito= null;
		if(optionalDistrito.isPresent())
			objDistrito = optionalDistrito.get();
		
		Optional<Estado> optionalEstado= estadoRepo.findById(empleadoPOJO.getCod_estado());
		Estado objEstado= null;
		if(optionalEstado.isPresent())
			objEstado = optionalEstado.get();
		
		Empleado empleado = new Empleado(
				empleadoPOJO.getCod_empleado(),
				objDistrito,
				objEstado,
				empleadoPOJO.getNombre(),
				empleadoPOJO.getApellidos(),
				empleadoPOJO.getDni(),
				empleadoPOJO.getDireccion(),
				empleadoPOJO.getTelefono(),
				empleadoPOJO.getEmail(),
				empleadoPOJO.getUsuario(),
				encriptador.encode(empleadoPOJO.getContrasena()), // <--- codificamos la contraseña
				roles
				);
		return empleadoRepo.save(empleado); // guarda el objeto y devuelve dicho objeto
	}

	@Override
	public Empleado getEmpleadoByCodigo(String codigo) {
		Optional<Empleado> optional = empleadoRepo.findById(codigo);
		Empleado empleado = null;
		if(optional.isPresent())
			empleado = optional.get();
		return empleado;
	}

	@Override
	public String getUltimoCodigo() {
//		DecimalFormat df = new DecimalFormat("00000");
		String codigo_ultimo = empleadoRepo.getUltimoCodigo();
		String codigo_nuevo = "EM10001";
		if(codigo_ultimo != null) {
			codigo_nuevo = "EM" + Integer.toString(Integer.parseInt(codigo_ultimo) + 1);
		}
		return codigo_nuevo;
	}
	
	@Override
	public void exportarReporte(String tipo, HttpServletResponse response) throws JRException, IOException {
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoRepo.listaPOJO());
		Map<String, Object> parametros = new HashMap();
		SimpleDateFormat formato1 = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy 'a las' HH:mm:ss");
		SimpleDateFormat formato2 = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Date fecha = new Date();
		String fecha1 = formato1.format(fecha);
		String fecha2 = formato2.format(fecha);
		
		String imagen = "logo_reporte_2.png";
		parametros.put("imagen_logo","src/main/resources/static/img/" + imagen);
		parametros.put("nombre_empresa","SHOES FOR MEN");
		parametros.put("direccion_empresa","AV. URUGUAY N 000 ");
		parametros.put("distrito_empresa","SAN ISIDRO");
		parametros.put("nombre_empleado","KEVIN B");
		parametros.put("ruc_empresa","55555555555");
		parametros.put("telefono_empresa","777-7777");
		parametros.put("fecha_generacion", fecha1);
		parametros.put("DataEmpleado", dataSource);
		
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/reporte_plantillas/rpt_empleado.jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parametros, new JREmptyDataSource());
		
		
		if(tipo.equalsIgnoreCase("pdf")) {
			JRPdfExporter exporter = new JRPdfExporter();
		    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		    response.setContentType("application/pdf");
		    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=listaEmpleado_" + fecha2 + ".pdf");
		    exporter.exportReport();
		}
		else if(tipo.equalsIgnoreCase("excel")) {
			JRXlsxExporter exporter = new JRXlsxExporter(); // la configuracion se hace en el mismo JASPER STUDIO
//		    SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
//		    reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
//		    reportConfigXLS.setDetectCellType(true);
//		    reportConfigXLS.setCollapseRowSpan(false);
//		    exporter.setConfiguration(reportConfigXLS); // anadimos la configuracion al archivo
		    exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); // 
		    response.setContentType("application/octet-stream");
		    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=listaEmpleado_" + fecha2 + ".xlsx");
		    exporter.exportReport();
		}
		
	}

	
	
}
