<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!--Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!--jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!--Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<!--Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- css loghi -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	
	<!-- css nostri -->
	<link rel="stylesheet" href="css/stileRegistrazione.css" type="text/css">
	
	<!-- javascript nostro -->
	<script src="javascript/controlloLogin.js"></script>
	
	<meta charset="UTF-8">

	<title>REGISTRAZIONE</title>
</head>
<body>
<div id="corpo">
		<div class="row">
			<div class="col-md-12"><!-- immagine raff-gaming -->
				<img src="immagini/logo.png" alt="RAAF-GAMING" style="width:15%; position: static; left:20px; top:20px; padding-left:20px; padding-top:10px;">
			</div>
		</div>
		
		<div class="row">
			
			<div class="col-md-4"></div>
			<div class="col-md-4" ><!-- form -->
				<div id="divForm">
					<form action="ServletRegistrazione" method="POST" id="stileForm" onSubmit="return controlloRegistrazione(this);">
						<h3 id="testoForm" style="margin-bottom:4%">INSERISCI I TUOI DATI :</h3>
						<div class="form-row">
	    					<div class="form-group col-md-6">
	      						<label class="spaziaturaSx" for="inputNome">Nome:</label>
	      						<input  type="text" class="form-control spaziaturaSx" id="inputNome" placeholder="Antonio" name="nome" style="border-radius:5px 5px 5px 5px; width:80%;" required>
	      					</div>
	      					<div class="form-group col-md-6">
	      						<label class="spaziaturaDx" for="inputCognome">Cognome:</label>
	      						<input type="text" class="form-control spaziaturaDx" id="inputCognome" placeholder="Rossi" name="cognome" style="border-radius:5px 5px 5px 5px; width:80%;" required>
	      					</div>
	    				</div>
	  					<div class="form-row">
	  						<div class="form-group col-md-6">
	      						<label class="spaziaturaSx" for="inputData">Data di nascita:</label>
	      						<input  type="date" class="form-control spaziaturaSx" id="inputData" placeholder="Antonio" name="data" style="border-radius:5px 5px 5px 5px; width:80%;" required>
	      					</div>
	      					<div class="form-group col-md-6">
	      						<label class="spaziaturaDx" for="inputIban">Iban:</label>
	      						<input  type="text" class="form-control spaziaturaDx" id="inputIban" placeholder="" name="Iban" style="border-radius:5px 5px 5px 5px; width:80%;" required>
	      					</div>
	  					</div>
	  					<div class="form-row">
	  						<div class="form-group col-md-6">
	      						<label class="spaziaturaSx" for="inputEmail">Email:</label>
	      						<input  type="text" class="form-control spaziaturaSx" id="inputEmail" placeholder="antoniorossi@gmail.com" name="email" style="border-radius:5px 5px 5px 5px; width:80%;" required>
	      					</div>
	      					<div class="form-group col-md-6">
	      						<label class="spaziaturaDx" for="inputPassword">Password:</label>
	      						<input  type="password" class="form-control spaziaturaDx" id="inputPassword" placeholder="********" name="password" style="border-radius:5px 5px 5px 5px; width:80%;" required>
	      					</div>
	      				</div>
	  					<div class="form-row">
	  						<div class="form-group col-md-12">
	      						<input   type="submit" class="form-control" id="invio" value="REGISTRATI!">
	      					</div>
	  					</div>
					</form>				
				</div>
					
			</div>
			<div class="col-md-4"></div>
			
		</div>
	
	
</div>

	
</body>
</html>