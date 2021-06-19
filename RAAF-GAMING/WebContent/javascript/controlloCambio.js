function controlloCambioCredenziali(x){
	var pass1=x.elements["password1"];
	var pass2=x.elements["password2"];
	if(pass1.value.length>0 && pass2.value.length>0){ //se ha scritto qualcosa nel campo nuova password
		if(pass1.value.length>=8){//se la password è valida
			pass1.style.border="2px solid green";
			if(pass1.value==pass2.value){ //lunghezza conferma password corretta e coincide con quella di prima

				pass2.style.border="2px solid green";
			} 
			else if(pass1.value!=pass2.value){
				alert("Le password non corrispondono");
				pass2.value="";
				pass2.style.border="2px solid red";
				return false;
			}
		}
		else{
			alert("password non valida");
		}
	}
	else if(pass1.value.length>0 && pass2.value.length==0){
		alert("Conferma la password");
		pass2.style.border="2px solid red";
		return false;
	}	
	
	var i=x.elements["iban"];
	if(i.value.length>0){ //se ha scritto qualcosa nel campo dell'iban
		if(i.value.length==27){//se l'iban è corretto
			i.style.border="2px solid green";
		}
		else{
			alert("Hai inserito un'Iban non valido");
			i.value="";
			i.style.border="2px solid red";
			return false;	
		}
	} 
	return true;
		
}