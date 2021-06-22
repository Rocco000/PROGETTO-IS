<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<script src="javascript/controlloProfilo.js"></script>
	
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
	String str = "servletprofilo";
	String url = response.encodeRedirectURL(str); //faccio l'url rewriting per non perdere la sessione
%>
<div class="container">
	<div class="row mt-3">
		<div class="col-md-6">
			<div class="card text-white" style=" border-style:none; border-radius:20px;">
				<img src="immagini/cartafedelta.png" class="rounded float-left card-img" alt="carta fedelta" style="height:300px;">
				<div class="card-img-overlay">
					<br><br><br>
					<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">EMAIL:</li>  <li class="list-inline-item">r.iuliano@gmail.com</li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">NOME:</li>  <li class="list-inline-item">rocco</li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">COGNOME:</li>  <li class="list-inline-item">iuliano</li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">IBAN:</li>  <li class="list-inline-item" id="ibanAggiornato">ITxxxxx</li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">CODICE CARTA:</li>  <li class="list-inline-item">123456789</li></ul>
				</div>
				
			</div>
		</div>
		<div class="col-md-6">
			
				<div class="form-row">
					<div class="form-group col-md-12">
						<p class="h2 text-center">Vuoi cambiare password e/o IBAN?</p>
						<p id="notifica" style="visibility:hidden; text-align:center; font-weight:bold;"></p>
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
		  			<div class="form-group col-md-12">
		  				<label for="inputIban1">Nuovo IBAN</label>
		  				<input type="text" name="iban" class="form-control w-50" id="inputIban1" placeholder="ITxxxxxxxxxxxxxxxxxxxxxxxxx">
		  			</div>
		  		</div>
		  		<div class="form-row">
		  			<div class="col-md-12">
		  				<input type="submit" class="btn btn-primary" value="INVIA" onclick="aggiornamentoCredenziali(this);">
		  			</div>
		  		</div>
			
		</div>
	</div>
	
	
</div>

<script>
	
	function aggiornamentoCredenziali(f){
		
		if(controlloCambioCredenziali()){

			var o= {
					passwordNuova: $("#inputPassword1").val(),
					ibanNuovo: $("#inputIban1").val()
			};
			alert(o.passwordNuova);
			alert(o.ibanNuovo);
			var parametriJson= JSON.stringify(o);
			$.ajax({
					
					type: "POST",
					url: "servletprofilo",
					contentType: "application/json",//tipo di dato che invio
					dataType:"text",//tipo di dati che mi aspetto dal server
					data: encodeURIComponent(parametriJson),
					async: true,
					success:function(data){//funzione invocata in caso di successo della richiesta 
								alert(""+data);
								alert("prima di parse");
								var risposta= JSON.parse(data);
								alert("dopo parse");
								alert(risposta.password+" "+risposta.iban);
								if(risposta.password==true){
									alert("password modificata");
									$("#notifica").css("visibility","visible");
									$("#notifica").html("PASSWORD MODIFICATA!");
									$("#notifica").css("color","green");
										
								}
								
								$("#ibanAggiornato").html(""+risposta.iban);
							},
					error: function(xhr,textStatus,errorText){
								alert("errore: "+textStatus+" "+errorText);
							}
			});
		}
	}
</script>

</body>
</html>