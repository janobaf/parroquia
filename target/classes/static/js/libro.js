const URL_API = 'http://localhost:8080/api/libro/';

function abrirModal(){
	$("#modalFormLibro").modal('show');
}

function obtenerDatosLibro(id){
	let html="";
	let idParseado = Number(id);
	var req = new XMLHttpRequest();
	req.open('GET', `http://localhost:8080/formLibro/${idParseado}`, true);
	req.onreadystatechange = function (aEvt) {
	  if (req.readyState == 4) {
	     if(req.status == 200){ 
	    	 html=(req.responseText);
	    	 pintarDatosModal(html);
	     }
	    	
	 
	  }
	};
	req.send(null);
	
}


function pintarDatosModal(dom){
	let domModal = new DOMParser().parseFromString(dom, "text/html");
	let domFrm = domModal.body.children[3].children[0].innerHTML;
	document.querySelector("#frmLibro").innerHTML=domFrm;
	$("#modalFormLibro").modal("show");
}