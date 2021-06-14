

function controlloValori(x){
	var e= x.elements["email"];
	
	var mailformat=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(e.value.match(mailformat)){//controllo formato email
		
		var p= x.elements["password"]; //controllo lunghezza password
		var valore= p.value.length;
		if(valore<5){
			alert("You have entered an invalid password!");
			p.value="";
			p.style.border="2px solid red";
			return false;
		}
		else
			return true;
		
	}else{
		alert("You have entered an invalid email address bello!");
		e.value="";
		e.style.border="2px solid red";
		return false;
	}
	
	
}



