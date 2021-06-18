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
	<script src="javascript/controlloCambio.js"></script>
	
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
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">IBAN:</li>  <li class="list-inline-item">ITxxxxx</li></ul>
    				<ul class="card-text list-inline font-weight-bold"><li class="list-inline-item text-warning">CODICE CARTA:</li>  <li class="list-inline-item">123456789</li></ul>
				</div>
				
			</div>
		</div>
		<div class="col-md-6">
			<form action="#" method="POST" onsubmit="return controlloCambioCredenziali(this);">
				<div class="form-row">
					<div class="form-group col-md-12">
						<p class="h2 text-center">Vuoi cambiare password e/o IBAN?</p>
					</div>
				</div>
		  		<div class="form-row">
		    		<div class="form-group col-md-6">
				      <label for="inputPassword3">Nuova password</label>
				      <input type="password" name="password1" class="form-control w-75" id="inputPassword3" placeholder="*******">
		    		</div>
		  			<div class="form-group col-md-6">
				      <label for="inputPassword4">Conferma password</label>
				      <input type="password" name="password2" class="form-control w-75" id="inputPassword4" placeholder="******">
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
		  				<input type="submit" class="btn btn-primary" value="INVIA">
		  			</div>
		  		</div>
			</form>
		</div>
	</div>
	
	
</div>

</body>
</html>