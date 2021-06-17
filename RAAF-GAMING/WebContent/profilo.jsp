<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Profilo</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!--Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!--jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!--Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<!--Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- css mio -->
	<link rel="stylesheet" href="css/stileProfilo.css" type="text/css">
</head>
<body style="background: url(../immagini/tlou.jpg) no-repeat;">

<div class="d-md-flex flex-md-row ml-3 mt-3" style="width:100%; height:40%;">
	<div class="col-md-6">
	
	<div class="flip-card">
		<div class="flip-card-inner">
	    	<div class="flip-card-front">
	    	
	      		<div class="card text-white" style="border-radius: 20px;">
					<img class="card-img" src="immagini/cartafedelta.png" alt="Carta fedelta">
					<div class="card-img-overlay">
					    <h5 class="card-title text-center font-weight-bold"  style="color:orange;">YOUR CARD</h5>
						<br><br>
					    <p class="card-text text-left font-weight-bold">NOME: </p>
					    <p class="card-text text-left font-weight-bold">COGNOME:</p>
					    <p class="card-text text-left font-weight-bold">DATA DI NASCITA:</p>
					    <p class="card-text text-left font-weight-bold">IBAN:</p>
					    <p class="card-text text-left font-weight-bold">EMAIL:</p>
					</div>
				</div>
				
	    	</div>
	    <div class="flip-card-back">
	    
	    	<div class="card text-white" style="border-radius: 20px;">
				<img class="card-img" src="immagini/cartafedelta.png" alt="Carta fedelta">
				<div class="card-img-overlay">
				    <h5 class="card-title text-center font-weight-bold"  style="color:orange;">YOUR CARD</h5>
					<br><br><br>
				    <p class="card-text text-left font-weight-bold">CODICE: </p>
				    <p class="card-text text-left font-weight-bold">PUNTI:</p>
				</div>
			</div>
	    
	    </div>
	  </div>
	</div> 
	
	</div><!-- colonna -->
	<div class="col-md-6">
	
	<form action="#" method="POST">
		<div class="form-group">
		    <label for="exampleInputPassword1">Nuova password</label>
		     <input type="password" name="password1" class="form-control" id="exampleInputPassword1" placeholder="********" style="width:70%;">
	  	</div>
	  	<div class="form-group">
		    <label for="exampleInputPassword2">Conferma password</label>
		    <input type="password" name="password2" class="form-control" id="exampleInputPassword2" placeholder="********" style="width:70%;">
	  	</div>
	  	<input type="submit" class="btn btn-primary" value="INVIA">
	</form>
	
	</div>
	
	
</div>

			

  
		   	

    


</body>
</html>