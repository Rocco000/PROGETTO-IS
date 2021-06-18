function controlloCambioCredenziali(x){
	
	var pass1=x.elements["password1"];
	var pass2=x.elements["password2"];
	var l1=pass1.value.lenght;
	var l2=pass2.value.lenght;
	alert(""+l1+" "+l2);
	if(l1>0 && l2>0){ //se ha scritto qualcosa nel campo nuova password
		alert("ciao");
		if(l1>=8){//se la password è valida
			pass1.style.border="2px solid green";
		
			
			if(pass2==pass1){ //lunghezza conferma password corretta e coincide con quella di prima
				pass2.style.border="2px solid green";
			} 
			else{
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
	else if(l1>0 && l2==0){
		alert("Conferma la password");
		pass2.style.border="2px solid red";
		return false;
	}	
	
	var i=x.elements["iban"];
	if(i.value.lenght>0){ //se ha scritto qualcosa nel campo dell'iban
		if(i.value.lenght==27){//se l'iban è corretto
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