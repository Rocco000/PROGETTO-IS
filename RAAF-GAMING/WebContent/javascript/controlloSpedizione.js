function controlloOrdini(x){
	x.elements["numeroOrdine"].style.border="2px solid green";
	x.elements["corriere"].style.border="2px solid green";
	const dataAttuale= new Date();
	const dataForm= new Date($("#consegnaOrdine").val());
	alert("data form "+dataForm.getFullYear());
	alert("data attuale "+dataAttuale.getFullYear());
	if(dataForm.getFullYear()==dataAttuale.getFullYear()){
		alert("anno uguale");
		if((dataForm.getMonth()+1)>=(dataAttuale.getMonth()+1)){
			if(dataForm.getDate()>=dataAttuale.getDate()){
			
				$("#consegnaOrdine").css("border","2px solid green");
				return true;				
			}
		}
		$("#consegnaOrdine").css("border","2px solid red");
		return false;
	}
	else if(dataForm.getFullYear()>dataAttuale.getFullYear())
	{
		alert("anno maggiore");
		$("#consegnaOrdine").css("border","2px solid green");
				return true;
	}
	else{
			$("#consegnaOrdine").css("border","2px solid red");
			return false;
		}
}