 /*BUSCAR EMPLEADO POR DNI*/
 function buscar(){
     var filtro=document.getElementById("filtro").value;
     const url="listarEmpleado";
     var urlPartida =window.location.href.split('/');
     window.location.href=urlPartida[0]+'//'+urlPartida[2]+'/'+url+'/'+filtro;           
}


