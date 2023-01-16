/*BUSCAR CLIENTE POR DNI*/            
function buscarDNI(){
	var filtro=document.getElementById("filtro").value;
	const url="listarLibroDetalle";
	var urlPartida =window.location.href.split('/');
	window.location.href=urlPartida[0]+'//'+urlPartida[2]+'/'+url+'/'+filtro;
            	
}