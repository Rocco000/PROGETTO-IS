

function controlloEmailPassword(x){
	var e= x.elements["email"];
	
	var mailformat=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(e.value.match(mailformat)){//controllo formato email
		e.style.border="2px solid green";
		var p= x.elements["password"]; //controllo lunghezza password
		var valore= p.value.length;
		if(valore<5){
			alert("Hai inserito una password errata");
			p.value="";
			p.style.border="2px solid red";
			return false;
		}
		else{
			p.style.border="2px solid green";
			return true;
		}
	}else{
		alert("Hai inserito un'email errata");
		e.value="";
		e.style.border="2px solid red";
		return false;
	}
	
	
}

function controlloRegistrazione(x){
	var n=x.elements["nome"];
	if(n.value.length<=15 && n.value.length>0){ //lunghezza nome corretta
		n.style.border="2px solid green";
		var c=x.elements["cognome"];
		if(c.value.length<=15 && c.value.length>0){ //lunghezza cognome corretta
			c.style.border="2px solid green";
			
			const dataAttuale= new Date();
			const dataForm= new Date(x.elements["data"].value);
			
			var eta=(dataAttuale.getFullYear()-dataForm.getFullYear());
			
			
			if(eta>=10 && eta<=110)
			{
			
				x.elements["data"].style.border="2px solid green";
				var i=x.elements["iban"];
				if(i.value.length==27 && i.value.length>0)
				{ //iban corretto
					i.style.border="2px solid green";
					return controlloEmailPassword(x);
				}
				else{
					alert("Hai inserito un'Iban non valido");
					i.value="";
					i.style.border="2px solid red";
					return false;
					}
				
			}
			else
			{
				x.elements["data"].style.border="2px solid red";
				x.elements["data"].value="";
				alert("Non hai l'età per registrarti!");
				return false;
			}
			
		
		} else{
			alert("Hai inserito un cognome non valido");
			c.value="";
			c.style.border="2px solid red";
			return false;
		}
	}else{
		alert("Hai inserito un nome non valido");
		n.value="";
		n.style.border="2px solid red";
		return false;
	}
}

