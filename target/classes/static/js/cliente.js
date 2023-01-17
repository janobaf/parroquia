/*PDF CLIENTES*/ 
function verPdfCliente(event){
            	event.preventDefault();
            	var req = new XMLHttpRequest();
            	req.open('GET', "http://localhost:8080/reportCliente/books", true);
            	req.onreadystatechange = function (aEvt) {
            	  if (req.readyState == 4) {
            	     if(req.status == 200){            	    	 
            	    	 decodificar(req.responseText);
            	     }            	    	
            	     else
            	      alert("Error loading page\n");
            	  }
            	};
            	req.send(null);           	
            	
            }
            
            
function decodificar(base64){
    var byteCharacters = atob(base64);
    var byteNumbers = new Array(byteCharacters.length);
    for (var i = 0; i < byteCharacters.length; i++) {
    byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    var byteArray = new Uint8Array(byteNumbers);
    var file = new Blob([byteArray], { type: 'application/pdf;base64' });
    var fileURL = URL.createObjectURL(file);
    window.open(fileURL);
}
           

/*BUSCAR CLIENTE POR DNI*/            
function buscar(){
	var filtro=document.getElementById("filtro").value;
	const url="listarCliente";
	var urlPartida =window.location.href.split('/');
	window.location.href=urlPartida[0]+'//'+urlPartida[2]+'/'+url+'/'+filtro;
            	
}