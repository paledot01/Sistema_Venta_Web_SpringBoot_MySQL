
var new_cod = "";

function listarEmpleado() {
	$.ajax({
		url: '/empleados/listarEmpleado', // direccion del controlador
		type: 'GET',
		success: function(result) { // data es lo que devuelve el metodo que esta dentro del cotrolador en este caso empleado.html
			$('#Contenedor').html(result);
			new_cod = $("#cod_empleado").val(); // guardamos el el nuevo codigo de empleado que se muestra en el modal
		},
		error: function(data, xml) { }
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
			alert("Datos guardados");
			$("#modalKevin").modal('hide'); // para mostrar show
			$('#Contenedor').html(result); // VUELVE A LLAMAR LA VISTA empledo.html ORIGINAL POR LO QUE CUALQUIER CAMBIO QUE LE HICIMOS DESAPARECE.
			new_cod = $("#cod_empleado").val();
		},
		error: function(data, xml) {
			alert("Error al guardar");
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
	alert(cod);
	$.ajax({
		url: '/empleados/eliminarEmpleado/'+param,
		type: 'GET',
//		data: { id: param }, // codigo es el nombre del parametro en el controlador
		contentType: "application/json; charset=utf-8", // lo que se envia
//		dataType: "json", --> detecta automaticamente el tipo de contenido que devuelve.

		success: function(result) {
			$('#Contenedor').html(result);
			alert("Empleado eliminado");
			new_cod = $("#cod_empleado").val(); // guardamos el nuevo codigo de empleado que se muestra en el modal
		},
		error: function(data, xml) {
			alert("Error al intentar eliminar");
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






