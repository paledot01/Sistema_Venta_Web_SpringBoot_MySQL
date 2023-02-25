// ---- ALERTAS -------------------
$(document).ready(function(){
	
	$("#alerta").css("transition",".4s"); // este estilo debi haberlo colocado en el CSS
	$("#alerta").css("z-index","1080");

});

function mostrar_check(titulo , mensaje){
	
	$("#alerta").css("opacity","1");
	$("#svg_check").addClass("d-flex");
	$("#svg_check").css("animation","trazo_01 1.3s .3s ease infinite");
	$("#alerta_titulo").text(titulo);
	$("#alerta_mensaje").text(mensaje);
	setTimeout(function(){
		$("#alerta").css("opacity","0");
		$("#svg_check").removeClass("d-flex");
		$("#svg_check").css("animation","sinAnimacion 1.3s .3s ease infinite");
	},3000);

}

function mostrar_cruz(titulo , mensaje){
		
	$("#alerta").css("opacity","1");
	$("#svg_cruz").addClass("d-flex");
	$("#svg_cruz_01").css("animation","trazo_02 1.3s .3s ease infinite");
	$("#svg_cruz_02").css("animation","trazo_03 1.3s .3s ease infinite");
	$("#alerta_titulo").text(titulo);
	$("#alerta_mensaje").text(mensaje);
	setTimeout(function(){
		$("#alerta").css("opacity","0");
		$("#svg_cruz").removeClass("d-flex");
		$("#svg_cruz_01").css("animation","sinAnimacion 1.3s .3s ease infinite");
		$("#svg_cruz_02").css("animation","sinAnimacion 1.3s .3s ease infinite");
	},3000);

}

function mostrar_candado(titulo , mensaje){
	
	$("#alerta").css("opacity","1");
	$("#svg_candado").addClass("d-flex");
	$("#svg_candado").css("animation","movimiento_3 1.5s .4s ease infinite");
	$("#sonido").css("animation","movimiento_1 1.5s .4s ease infinite");
	$("#cerradura").css("animation","movimiento_2 1.5s .4s ease infinite");
	$("#alerta_titulo").text(titulo);
	$("#alerta_mensaje").text(mensaje);
	setTimeout(function(){
		$("#alerta").css("opacity","0");
		$("#svg_candado").removeClass("d-flex");
		$("#svg_candado").css("animation","sinAnimacion 1.5s .4s ease infinite");
		$("#sonido").css("animation","sinAnimacion 1.5s .4s ease infinite");
		$("#cerradura").css("animation","sinAnimacion 1.5s .4s ease infinite");
	},3500);

}

// NOTA: EN LA MISMA FUNCION NO PUEDE CAMBIAR EL DISPLAY DE NONDE A BLOCK Y ALA VES LA OPCACIDAD Y ESPERAR QUE FUNCIONE LA TRANSICION, LA CAMBIO QUE QUIERES QUE SE APLIQUE LA TRANSICION DEBE ESTAR SOLA

// ---------------------------------

function logear(){
	console.log($("#form_login").serialize());
	$.ajax({
		url: "/login",
		type: "POST",
		beforeSend: function(xhr){
                    xhr.withCredentials = true;
        	},
		data: $("#form_login").serialize(),
		success: function(result, status, xx) {

			alert("Datos guardados");
			console.log(result);
			console.log(status);
			console.log(xx);
			//console.log(xx.getAllResponseHeaders());
			//location.href = "/login?error";
		},
		error: function(data, xml) {
			alert(errorrrrr);
		}
	});
}


var new_cod = "";

function listarEmpleado() {
	$.ajax({
		url: '/empleados/listarEmpleado', // direccion del controlador
		type: 'GET',
		success: function(result) { // data es lo que devuelve el metodo que esta dentro del cotrolador en este caso empleado.html
			$('#Contenedor').html(result);
			new_cod = $("#cod_empleado").val(); // guardamos el el nuevo codigo de empleado que se muestra en el modal
		},
		error: function(data, xml) { 
			alerta("error en listarEmpleado()");
		 }
	});
}

function agregarEmpleado() {

	console.log($("#frmEmpleado").serialize());
	$.ajax({
		url: '/empleados/guardarEmpleado',
		type: 'POST',
//		data: JSON.stringify(obj),
		data: $("#frmEmpleadoAgregar").serialize(), // el formulario ya toma todos los valores mapeados listo para enviar al controlador
//		contentType: "application/json; charset=utf-8",
		success: function(result) {
			//alert("Datos guardados");
			mostrar_check("Felicitaciones: " , "Los datos fueron agregados");
			$("#modalKevin").modal('hide'); // para mostrar show
			$('#Contenedor').html(result); // VUELVE A LLAMAR LA VISTA empledo.html ORIGINAL POR LO QUE CUALQUIER CAMBIO QUE LE HICIMOS DESAPARECE.
			new_cod = $("#cod_empleado").val();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			
			if(jqXHR.status == 400 ){
				mostrar_cruz("Error: ", "Error al registrar los datos");
			} else if(jqXHR.status == 403){
				mostrar_candado("Mensaje: ", "Acceso Denegado");
			}
			
			console.log(jqXHR.status);
			console.log(textStatus);
			
		}
	});
}


function mostrarEditarEmpleado(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11){
	console.log(p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7 + " " + p8 + " " + p9 + " " + p10 + " " + p11);
	
	$(".modal-title").text("Editar Empleado");
//	$(".modal-header").css("background-color", "#28a745");
//	$("#cod_empleado").attr({
//		"readonly":true
//	});
	$("#cod_empleado").val(p1);
	$("#cod_distrito").val(p2);
	$("#cod_estado").val(p3);
	$("#nombre").val(p4);
	$("#apellidos").val(p5);
	$("#dni").val(p6);
	$("#direccion").val(p7);
	$("#telefono").val(p8);
	$("#email").val(p9);
	$("#usuario").val(p10);
	$("#contrasena").val("");
	console.log(new_cod);
}

function eliminarEmpleado(cod) { // este cod llega como string
	var param = cod;
	//alert(cod);
	$.ajax({
		url: '/empleados/eliminarEmpleado/'+param,
		type: 'GET',
//		data: { id: param }, // codigo es el nombre del parametro en el controlador
		contentType: "application/json; charset=utf-8", // lo que se envia
//		dataType: "json", --> detecta automaticamente el tipo de contenido que devuelve.

		success: function(result) {
			$('#Contenedor').html(result);
			//alert("Empleado eliminado");
			mostrar_check("Felicitaciones: " , "Datos eliminados correctamente");
			new_cod = $("#cod_empleado").val(); // guardamos el nuevo codigo de empleado que se muestra en el modal
		},
		error: function(jqXHR, textStatus, errorThrown) {
			if(jqXHR.status == 403){
				mostrar_candado("Mensaje: ", "Acceso Denegado");
			}
			console.log(jqXHR.status);
			console.log(textStatus);
		}
	});
}

function cerrarModal(){
	$(".modal-title").text("Agregar Empleado");
//	$("#cod_empleado").attr({
//		"readonly":false
//	});
	
	$("#cod_empleado").val(new_cod);
//	$("#cod_distrito").val(p2);
//	$("#cod_estado").val(p3);
	$("#nombre").val("");
	$("#apellidos").val("");
	$("#dni").val("");
	$("#direccion").val("");
	$("#telefono").val("");
	$("#email").val("");
	$("#usuario").val("");
	$("#contrasena").val("");	
}

/*
	Cuando se envia por GET se visualiza lo que se envia por eso los parametros van en la misma URL
	Cuando se envia por POST para que no se visualice se debe enviar como JSON

	el profe envia el GET como objeto y en el controlador lo recibe como request
*/






