function controlloProdEsistente(x){
	var quantita=$("#quantitaProdottoEx").val().length;
	if($("#quantitaProdottoEx").val()>=1 && quantita>=1 && quantita<=9){
		$("#quantitaProdottoEx").css("border","2px solid green");
		return true;
	}
	else{
		$("#quantitaProdottoEx").css("border","2px solid red");
		return false;
	}
	
}