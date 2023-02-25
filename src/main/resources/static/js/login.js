// ---- ALERTAS -------------------
$(document).ready(function(){
	
	$("#alerta").css("transition",".4s"); // este estilo debi haberlo colocado en el CSS
	// login
	$("#alerta_log_check").css("transition",".4s");
	setTimeout(mostrar_log_check, 200); // esperamos esta funcion 1 seg antes que se active
	$("#alerta_log_cruz").css("transition",".4s");
	setTimeout(mostrar_log_cruz, 200);
});

function mostrar_log_check(){
		
	$("#alerta_log_check").css("opacity","1");
	$("#svg_check").addClass("d-flex");
	$("#svg_check").css("animation","trazo_01 1.3s .3s ease infinite");
	setTimeout(function(){
		$("#alerta_log_check").css("opacity","0");
		$("#svg_check").removeClass("d-flex");
		$("#svg_check").css("animation","sinAnimacion 1.3s .3s ease infinite");// modifica la animacion que le dimos por otra que no existe.
	},3500);

}

function mostrar_log_cruz(){
		
	$("#alerta_log_cruz").css("opacity","1");
	$("#svg_cruz").addClass("d-flex");
	$("#svg_cruz_01").css("animation","trazo_02 1.3s .3s ease infinite");
	$("#svg_cruz_02").css("animation","trazo_03 1.3s .3s ease infinite");
	setTimeout(function(){
		$("#alerta_log_cruz").css("opacity","0");
		$("#svg_cruz").removeClass("d-flex");
		$("#svg_cruz_01").css("animation","sinAnimacion 1.3s .3s ease infinite");
		$("#svg_cruz_02").css("animation","sinAnimacion 1.3s .3s ease infinite");
	},3500);

}