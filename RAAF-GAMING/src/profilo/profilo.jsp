<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="profilo.ClienteBean,profilo.CartaFedeltaBean"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!--Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!--jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!--Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<!--Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- javascript nostro -->
	<script src="javascript/controlloCambio.js"></script>
	
	<!-- Css navbar -->
	<link rel="stylesheet" href="css/StileIndex.css" type="text/css">
	
	<!-- script per il carrello -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>
	
	<title>PROFILO</title>
	<style>
		body{
			width: 100%;
			height: 100%;	
			background: url(immagini/sky.jpg) no-repeat;
			background-attachment: fixed;		
			z-index: -1;	
			-webkit-background-size: cover;	
			-moz-background-size: cover;	
			-o-background-size: cover;	
			background-size: cover;
			color:white;
		}
	</style>
</head>
<body>
<%
	//ottengo il campo visitato per vedere se son passato nella servletAccessoProfilo
	String visitato= (String)request.getAttribute("visitato");
	if(visitato==null){
		//se è null vado in servletAccessoProfilo
		
		String url="servletaccessoprofilo";
		url= response.encodeURL(url);
		response.sendRedirect(url);
		return;
	}
	
	ClienteBean utente= (ClienteBean)request.getAttribute("datiUtenteCarta");
	CartaFedeltaBean carta= (CartaFedeltaBean)request.getAttribute("puntiCarta");
	String str = "servletprofilo";
	String url = response.encodeRedirectURL(str); //faccio l'url rewriting per non perdere la sessione
%>

<%@include file="../it/unisa/utility/navbar.jsp" %>
<div class="container" style="min-height:100vh;">
	<div class="row mt-3">
		<div class="col-md-6">
			<div class="card text-white" style=" border-style:none; border-radius:20px;">
				<img src="immagini/cartafedelta.png" class="rounded float-left card-img" alt="carta fedelta" style="height:350px;">
				<div class="card-img-overlay">
					<br><br><br>
					<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">EMAIL:</li>  <li class="list-inline-item"><%=utente.getEmail() %></li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">NOME:</li>  <li class="list-inline-item"><%=utente.getNome() %></li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">COGNOME:</li>  <li class="list-inline-item"><%=utente.getCognome() %></li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">CODICE CARTA DI CREDITO:</li>  <li class="list-inline-item" id="cartaAggiornata"><%="****"+utente.getCartadicredito().substring(12,16) %></li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">CODICE CARTA:</li>  <li class="list-inline-item"><%=utente.getCarta_fedelta() %></li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">PUNTI CARTA:</li>  <li class="list-inline-item"><%=carta.getPunti() %></li></ul>
				</div>
				
			</div>
		</div>
		<div class="col-md-6">
			
				<div class="form-row">
					<div class="form-group col-md-12">
						<p class="h2 text-center">Vuoi cambiare password e/o IBAN?</p>
						<p id="notifica" style="visibility:hidden; font-weight:bold; background-color:white;"></p>
					</div>
				</div>
		  		<div class="form-row">
		    		<div class="form-group col-md-6">
				      <label for="inputPassword1">Nuova password</label>
				      <input type="password" name="password1" class="form-control w-75" id="inputPassword1" placeholder="*******">
		    		</div>
		  			<div class="form-group col-md-6">
				      <label for="inputPassword2">Conferma password</label>
				      <input type="password" name="password2" class="form-control w-75" id="inputPassword2" placeholder="******">
		  			</div>
		  		</div>
		  		<div class="form-row">
		  			<div class="form-group col-md-4">
		  				<label for="inputCarta1">Numero carta</label>
		  				<input type="text" name="numeroCarta" class="form-control w-50" id="inputCarta1" placeholder="xxxxxxxxxxxxxxxx">
		  			</div>
		  			<div class="form-group col-md-4">
		  				<label for="inputCarta2">CVV</label>
		  				<input type="text" name="cvvCarta" class="form-control w-50" id="inputCarta2" placeholder="xxx">
		  			</div>
		  			<div class="form-group col-md-4">
		  				<label for="inputCarta3">Data scadenza</label>
		  				<input type="date" name="cvvCarta" class="form-control w-50" id="inputCarta3">
		  			</div>
		  		</div>
		  		<div class="form-row">
		  			<div class="col-md-12">
		  				<input type="submit" class="btn btn-primary" value="INVIA" onclick="aggiornamentoCredenziali();">
		  			</div>
		  		</div>
			
		</div>
	</div>
	
	
</div>

<%@include file="../it/unisa/utility/footer.jsp" %>

<%
	String urlj= response.encodeURL("servletprofilo");
%>
<script>
	
	function aggiornamentoCredenziali(){
		alert("Nella funzione javascript di profilo.jsp");
		var pass1= document.getElementById("inputPassword1");
		var pass2= document.getElementById("inputPassword2");
		var codiceCarta= document.getElementById("inputCarta1");
		var cvv= document.getElementById("inputCarta2");
		var data= document.getElementById("inputCarta3");
		alert(pass1.value+" "+pass2.value+" "+codiceCarta.value+" "+cvv.value+" "+data.value);
		if(controlloCambioCredenziali(pass1,pass2,codiceCarta,cvv,data)){
			alert("Controllo valori fatto");
			var o= {
					passwordNuova: $("#inputPassword1").val(),
					cartaNuova: $("#inputCarta1").val(), //qui bisogna mettere i campi giusti
					codiceNuovo: $("#inputCarta2").val(),
					dataScadNuova: $("#inputCarta3").val()
			};
			var parametriJson= JSON.stringify(o);
			
			$.ajax({
					
					type: "POST",
					url: "<%=urlj %>",
					contentType: "application/json",//tipo di dato che invio
					dataType:"text",//tipo di dati che mi aspetto dal server
					data: encodeURIComponent(parametriJson),
					async: true,
					success:function(data){//funzione invocata in caso di successo della richiesta 
								var risposta= JSON.parse(data);
								if(risposta.errorMessage!=null){
									$("#notifica").css("visibility","visible");
									$("#notifica").html(""+risposta.errorMessage);
									$("#notifica").css("color","red");
									$("#notifica").css("text-align","center");
								}
								else{
									if(risposta.password==true){
										alert("password modificata");
										$("#notifica").css("visibility","visible");
										$("#notifica").html("PASSWORD MODIFICATA!");
										$("#notifica").css("color","green");
										$("#notifica").css("text-align","center");
										
									}
									if(risposta.carta!=null)
										$("#cartaAggiornata").html(""+risposta.carta); 
								}
							}
			});
		}
	}
</script>

</body>
</html>