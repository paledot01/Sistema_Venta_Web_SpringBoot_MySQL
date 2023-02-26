package com.edu.cibertec.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.cibertec.model.Empleado;
import com.edu.cibertec.model.EmpleadoPOJO;
import com.edu.cibertec.service.DistritoService;
import com.edu.cibertec.service.EmpleadoService;
import com.edu.cibertec.service.EstadoService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/empleados") //--> "/empleado/listarEmpleado" 
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoServ;
	@Autowired
	private DistritoService distritoServ;
	@Autowired
	private EstadoService estadoServ;


	
	@GetMapping("/listarEmpleado")
	public String listarEmpleado(Model modelo) {
		modelo.addAttribute("lista_empleado", empleadoServ.listar());// <-- aca se envia toda la lista de los empleados que se mostrara tanto en la tabla como en el modal para EDITAR los valores
		modelo.addAttribute("lista_distrito", distritoServ.listar());
		modelo.addAttribute("lista_estado", estadoServ.listar());
		modelo.addAttribute("ultimo_codigo", empleadoServ.getUltimoCodigo());
		
		return "empleado"; // esto es la data lo que devuelve " empleado.html "
	}

	@GetMapping("/eliminarEmpleado/{id}")
	public String eliminarEmpleado(@PathVariable("id") String id, Model modelo) {
//	    Empleado empleado= empleadoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		Empleado empleado= empleadoServ.getEmpleadoByCodigo(id);
	    empleadoServ.eliminar(empleado);
	    return "redirect:/empleados/listarEmpleado";
	}
		
	@ModelAttribute("empleadoPOJO") // pasa defrente a la vista sin que lo mandes, creo que todos los "model" lo hacen
	public EmpleadoPOJO getEmpleadoPOJO() {
		EmpleadoPOJO empleadoPOJO = new EmpleadoPOJO();
		return empleadoPOJO;
	}
	
    @PostMapping("/guardarEmpleado") // este añade o actualiza un empleado
    public String addUser(@ModelAttribute("empleadoPOJO") EmpleadoPOJO empPOJO ) {
    	empleadoServ.guardar(empPOJO);
        return "redirect:/empleados/listarEmpleado";
    }
    
    @GetMapping("/reporteEmpleadoEXCEL")
    private ResponseEntity<Void> reporteEmpleadoEXCEL(HttpServletResponse response) throws JRException,IOException{
    	empleadoServ.exportarReporte("excel", response);
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping(value = "/reporteEmpleadoPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    private ResponseEntity<Void> reporteEmpleadoPDF(HttpServletResponse response) throws JRException,IOException{
    	empleadoServ.exportarReporte("pdf", response);
    	return ResponseEntity.ok().build();
    }
    
    /* Redirect manada a aejecutar la otra direccion  o meotod de este mismo controlador que sera la que finalmente terminara mostrando 
     * la pagina visual, osea el redirect no envia a una pagina si no que se la encarga a la nueva direccion */
   
    
    
    
//    @GetMapping(value = "/reporteEmpleadoPDF", produces = MediaType.APPLICATION_PDF_VALUE)
//    private ResponseEntity<byte[]> reporteEmpleadoPDF() throws JRException,IOException{
//    	
//    	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoServ.listaReportePOJO());
//		Map<String, Object> parametros = new HashMap();
//		SimpleDateFormat formato1 = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy 'a las' HH:mm:ss");
//		SimpleDateFormat formato2 = new SimpleDateFormat("yyyyMMdd-HHmmss");
//		Date fecha = new Date();
//		String fecha1 = formato1.format(fecha);
//		String fecha2 = formato2.format(fecha);
//		
//		String imagen = "logo_reporte_3.png";
//		parametros.put("imagen_logo","src/main/resources/static/img/" + imagen);
//		parametros.put("nombre_empresa","SHOES FOR MEN");
//		parametros.put("direccion_empresa","AV. URUGUAY N 389 ");
//		parametros.put("distrito_empresa","SAN ISIDRO");
//		parametros.put("nombre_empleado","KEVIN BASILIO");
//		parametros.put("ruc_empresa","12345678901");
//		parametros.put("telefono_empresa","123-4567");
//		parametros.put("fecha_generacion", fecha1);
//		parametros.put("DataEmpleado", dataSource);
//		
//		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/reporte_plantillas/rpt_empleado.jrxml"));
//		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parametros, new JREmptyDataSource());
//		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
//		//System.err.println(data + "error kevin"); // solo sirve para resaltar un mensaje como si fuera error pero no necesariamente lo es.
//		
//	    HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", "attachment; filename=listaEmpleado_" + fecha2 + ".pdf"); // inline - muestra en el mismo browser, attachment - defrente descarga localmente
//
//		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
//    }
    
    
//    @GetMapping("/reporteEmpleadoEXCEL")
//    private void reporteEmpleadoEXCEL(HttpServletResponse response) throws JRException,IOException{
//    	
//    	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoServ.listaReportePOJO());
//		Map<String, Object> parametros = new HashMap();
//		SimpleDateFormat formato1 = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy 'a las' HH:mm:ss");
//		SimpleDateFormat formato2 = new SimpleDateFormat("yyyyMMdd-HHmmss");
//		Date fecha = new Date();
//		String fecha1 = formato1.format(fecha);
//		String fecha2 = formato2.format(fecha);
//		
//		String imagen = "logo_reporte_2.png";
//		parametros.put("imagen_logo","src/main/resources/static/img/" + imagen);
//		parametros.put("nombre_empresa","SHOES FOR MEN");
//		parametros.put("direccion_empresa","AV. URUGUAY N 389 ");
//		parametros.put("distrito_empresa","SAN ISIDRO");
//		parametros.put("nombre_empleado","KEVIN BASILIO");
//		parametros.put("ruc_empresa","12345678901");
//		parametros.put("telefono_empresa","123-4567");
//		parametros.put("fecha_generacion", fecha1);
//		parametros.put("DataEmpleado", dataSource);
//		
//		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/reporte_plantillas/rpt_empleado.jrxml"));
//		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parametros, new JREmptyDataSource());
//		
//		
//		response.setContentType("application/octet-stream");
//		JRXlsxExporter exporter = new JRXlsxExporter();
//	    SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
////	    reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
//	    reportConfigXLS.setDetectCellType(true);
//	    reportConfigXLS.setCollapseRowSpan(false);
//	    exporter.setConfiguration(reportConfigXLS); // anadimos la configuracion al archivo
//	    exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); // 
//	    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
//	    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=listaEmpleado_" + fecha2 + ".xlsx");
//	    
//	    exporter.exportReport();
//
//    }
    
//    @GetMapping("/reporteEmpleadoEXCEL")
//    private void reporteEmpleadoEXCEL(HttpServletResponse response) throws JRException,IOException{
//    	empleadoServ.exportarReporteEXCEL(response);
//    }
    

    
//    @GetMapping("/reporteEmpleadoHTML")
//	private void reporteEmpleadoHTML(HttpServletResponse response) throws Exception {
//		
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoServ.listaReportePOJO()); //Cual es mi colección de datos o fuentes de datos
//		Map<String, Object> parametros = new HashMap();
//		String imagen = "logo_reporte.png";
//		parametros.put("imagen_logo","src/main/resources/static/img/" + imagen);
//		parametros.put("nombre_empresa","SHOES FOR MEN");
//		parametros.put("direccion_empresa","AV. URUGUAY N 389 ");
//		parametros.put("distrito_empresa","SAN ISIDRO");
//		parametros.put("nombre_empleado","KEVIN BASILIO");
//		parametros.put("ruc_empresa","12345678901");
//		parametros.put("DATAEmpleado", dataSource);
//		//indicar cual es Content Type de salida
//		response.setContentType("text/html");
//		InputStream inputStream = this.getClass().getResourceAsStream("/reporte_plantillas/reporte_empleado.jrxml");
//		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
//		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//		exporter.exportReport();
//	}
	
}
