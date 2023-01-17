function obtenerDatosGet(url,callback){
			 var myHeaders = new Headers();
			  var myInit = { method: 'GET',
			                 headers: myHeaders,
			                 mode: 'cors',
			                 cache: 'default' };

			  var myRequest = new Request(url, myInit);

			  		fetch(myRequest)
			  		  .then(function(response) {
			  			  debugger;
			  			  console.log(response);
			  		    return response;
			  		  })
			  		  .catch(function(error) {
			  			  console.log('Hubo un problema con la petición Fetch:' + error.message);
			  			});	
}
