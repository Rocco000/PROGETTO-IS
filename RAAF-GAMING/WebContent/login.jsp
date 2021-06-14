<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="css/stileLogin.css" type="text/css">
	
	<!-- javascript nostro -->
	<script src="javascript/controlloLogin.js"></script>
	
	<meta charset="UTF-8">

	<title>LOGIN</title>
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
					<form action="servletlogin" method="POST" id="stileForm" onSubmit="return controlloValori(this);">
						<h3 id="testoForm">BENVENUTO GIOCATORE!</h3>
							<%
							String str=(String)request.getAttribute("message");
							if(str!=null){
								%>
								<h5 style="color:red; text-align:center;">Email/Password errata!</h5>
								<%
							}
							%>
						<div class="input-group mb-3" style="margin-top:10%; width:80%; margin-left:10%">
  							<div class="input-group-prepend">
    							<span class="input-group-text" id="basic-addon1">
    								<i class='fas fa-user-alt' style='font-size:20px'></i>
    							</span>
  							</div>
  							<input type="text" class="form-control" style="border-radius:0px 5px 5px 0px;" placeholder="email" name="email">
  						</div>

	  					<div class="input-group mb-3" style="margin-top:5%; width:80%; margin-left:10%">
	  						<div class="input-group-prepend">
    							<span class="input-group-text" id="basic-addon2">
    								<i class="fa fa-lock" style="font-size:20px"></i>
    							</span>
  							</div>
	  						<input type="password" name="password" class="form-control" style="border-radius:0px 5px 5px 0px;" placeholder="************"><br>
	  					</div>
	  					
	  					 <div class="form-check mb-2 mr-sm-2" style="margin-left:10%;">
    						<input class="form-check-input" type="checkbox" name="collegato" id="inlineFormCheck">
    						<label class="form-check-label" for="inlineFormCheck">
      							Resta collegato
    						</label>
  						</div>
	  					
	  					<input type="submit" value="ACCEDI" id="invio"><br>
	  					<p>oppure <a href="#" target="_self">REGISTRATI</a></p>
	  					
					</form>				
				</div>
	
			</div>
			<div class="col-md-4"></div>
			
		</div>
	
	
</div>

	
</body>
</html>