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
	<link rel="stylesheet" href="css/stileAdmin.css" type="text/css">
	
	<!-- javascript nostro -->
	<script src="javascript/controlloLogin.js"></script>
	
	<meta charset="UTF-8">

	<title>Pagina-Amministrazione</title>
</head>
<body>	
		<div class="container ml-5 mb-3 mt-2">
		<img src="immagini/logo.png" alt="RAAF-GAMING" style="width:180px; position: static;">
		<a class="btn btn-dark mr-3 mt-5 " style="float:right" href=/servletlogoutadmin role="button">LogOut</a>
		</div>
		<h4 class="testo" style="font-family: Acunim Variable Consent;text-align:center">Inserisci prodotto o crea spedizione:</h4>
		<div class="form-row d-flex justify-content-center">
		<label>Nuovo prodotto
			<input type="radio" id="nuovoProdotto"name="sceltaP" onchange="formEsistente()"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>Prodotto esistente
			<input type="radio" id="esistenteProdotto"name="sceltaP" onchange="formEsistente()"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>Crea spedizione
			<input type="radio" id="spedizioneProdotto"name="sceltaP" onchange="spedizioneProdotto()"></label>
			</div>
		<div class="container d-flex justify-content-center" style="background-color: rgba(254,254,233,0.5);border-radius:20px;">
		<div id="ordine" style="display:none">
		<form>
		<div class="form-row">
		<div class="form-group mr-3 mt-3">
	    <label for="nomeProdotto">Codice ordine:</label><br>
	    <select class="form-select" style="border-radius:8px;height:35px;width:110px" name="numeroOrdine">
  		<option value="1">Bartolini</option>
  		<option value="2">DHL</option>
  		<option value="3">SDA</option>
  		<option value="4">TNT</option>
  		<option value="5">UPS</option>
		</select>
	    </div>
	    <div class="form-group mr-3 mt-3">
	    <label for="nomeProdotto" >Corriere espresso:</label><br>
	    <select class="form-select" style="border-radius:8px;height:35px;width:110px" name="corriere">
  		<option value="1">Bartolini</option>
  		<option value="2">DHL</option>
  		<option value="3">SDA</option>
  		<option value="4">TNT</option>
  		<option value="5">UPS</option>
		</select>
	    </div>
	    <div class="form-group mr-3 mt-3">
	    <label for="nomeProdotto">Data consegna:</label>
	    <input type="date" class="form-control" id="consegnaOrdine" name="consegnaO" style="border-radius:10px">
	    </div>
	     <div class="form-row">
	     <button class="btn btn-dark ml-3 mt-5" style="border-radius:5px;height:32px">Conferma</button>
	     </div>
	    </div>
		</form>
		</div>
		</div>
		<div class="container d-flex justify-content-center" style="background-color: rgba(254,254,233,0.5);border-radius:20px;">
		<div id="esistente" style="display:none">
		<form>
		<div class="form-row">
		<div class="form-group mr-3 mt-3">
	    <label for="nomeProdotto">Codice:</label>
	    <input type="text" class="form-control" id="codiceProdotto" name="codicePnuovo" style="border-radius:10px">
	    </div>
	    <div class="form-group mr-3 mt-3">
	    <label for="nomeProdotto">Quantità:</label>
	    <input type="text" class="form-control" id="quantitaProdotto" name="quantitaPnuovo" style="border-radius:10px">
	    </div>
	     <div class="form-row">
	     <button class="btn btn-dark ml-3 mt-5" style="border-radius:5px;height:32px">Inserisci</button>
	     </div>
	    </div>
		</form>
		</div>
		<div id=nuovo style="display:none">
		<form>
			<div class="form-row">		
	  <div class="form-group mr-3">
	    <label for="nomeProdotto">Nome:</label>
	    <input type="text" class="form-control" id="nomeProdotto" name="nomeP" style="border-radius:10px">
	  </div>
	  <div class="form-group">
	    <label for="codiceProdotto">Prezzo:</label>
	    <input type="text" class="form-control" id="prezzoProdotto"name="prezzoP" style="border-radius:10px">
	  </div>
	  <div class="form-group ml-3">
	    <label for="codiceProdotto">Sconto:</label>
	    <input type="text" class="form-control" id="scontoProdotto"name="scontoP" style="border-radius:10px">
	  </div>
	  </div>
	   <div class="form-row">		
	  <div class="form-group mr-3">
	    <label for="nomeProdotto">Data uscita:</label>
	    <input type="date" class="form-control" id="uscitaProdotto" name="dataP" style="border-radius:10px">
	  </div>
	  <div class="form-group">
	    <label for="codiceProdotto">Fornitore:</label>
	    <input type="text" class="form-control" id="fornitoreProdotto"name="fornitoreP" style="border-radius:10px">
	  </div>
 		<div class="form-group ml-3">
	    <label for="nomeProdotto">Quantita da rifornire:</label>
	    <input type="text" class="form-control" id="quantitaProdotto" name="quantitaP" style="border-radius:10px">
	  </div>
	  </div>
	  <div class="form-row">		
		<div class="form-group">
    	<label for="exampleFormControlFile1">Copertina:</label>
    	<input type="file" class="form-control-file" id="copertinaP" >
    	</div>
  	</div>
			<div class="form-group mt-3">
			<label>VIDEOGIOCO
			<input type="radio" id="videogiocoRadio"name="sceltaP" onchange="prodottoForm();"></label>&nbsp;&nbsp;&nbsp;
			<label>CONSOLE
			<input type="radio" id="consoleRadio"name="sceltaP" onchange="prodottoForm()"></label>&nbsp;&nbsp;&nbsp;
			<label>DLC
			<input type="radio" id="dlcRadio"name="sceltaP" onchange="prodottoForm()"></label>&nbsp;&nbsp;&nbsp;
			<label>ABBONAMENTO
			<input type="radio"id="abbonamentoRadio"name="sceltaP" onchange="prodottoForm()"></label>&nbsp;&nbsp;&nbsp;
			</div>
			<div>
			<div id="videogiocoForm" class="form-group" style="display:none">
			<label>Dimensione:<input type="text" name="dimensioni" id="dim" style="border-radius:7px"></label>
			<label>Pegi:<input type="text" name="pegi" style="border-radius:7px"></label>
			<label>Numero di cd:<input type="text" name="ncd" style="border-radius:7px"></label>
			<label>Chiave:<input type="text" name="chiave" style="border-radius:7px"></label>
			<label>Software House:<input type="text" name="nomesfh" style="border-radius:7px"></label>
			<label>Edizione limitata:<input type="number" name="ncd" min="0" max="1" style="border-radius:7px"></label>
			<button class="btn btn-dark ml-3" style="border-radius:5px">Inserisci</button>
			</div>
			<div id="consoleForm" class="form-group" style="display:none">
			<label>Specifiche:<input type="text" name="specifiche" id="dim" style="border-radius:7px"></label>
			<label>Colore:<input type="text" name="colore" style="border-radius:7px"></label>
			<button class="btn btn-dark ml-3" style="border-radius:7px">Inserisci</button>
			</div>
			<div id="dlcForm" class="form-group" style="display:none">
			<label>Descrizione:<input type="text" name="descrizione" id="dim" style="border-radius:7px"></label>
			<label>Dimensione:<input type="text" name="dimensioneDlc" style="border-radius:7px"></label>
			<button class="btn btn-dark ml-3" style="border-radius:7px">Inserisci</button>
			</div>
			<div id="abbonamentoForm" class="form-group" style="display:none">
			<label>Codice:<input type="text" name="codice" id="dim" style="border-radius:7px"></label>
			<label>Durata:<input type="text" name="durata" style="border-radius:7px"></label>
			<button class="btn btn-dark ml-3" style="border-radius:5px">Inserisci</button>
			</div>
			</div>
			</form>
			</div>
		</div>
		<script>
		function prodottoForm(){
		if(document.getElementById("videogiocoRadio").checked){
			document.getElementById("videogiocoForm").style.display="block";
			document.getElementById("consoleForm").style.display="none";
			document.getElementById("dlcForm").style.display="none";
			document.getElementById("abbonamentoForm").style.display="none";
		}else if(document.getElementById("consoleRadio").checked){
			document.getElementById("consoleForm").style.display="block";
			document.getElementById("videogiocoForm").style.display="none";
			document.getElementById("dlcForm").style.display="none";
			document.getElementById("abbonamentoForm").style.display="none";
		}else if(document.getElementById("dlcRadio").checked){
			document.getElementById("dlcForm").style.display="block";
			document.getElementById("videogiocoForm").style.display="none";
			document.getElementById("consoleForm").style.display="none";
			document.getElementById("abbonamentoForm").style.display="none";
		}else if(document.getElementById("abbonamentoRadio").checked){
			document.getElementById("abbonamentoForm").style.display="block";
			document.getElementById("videogiocoForm").style.display="none";
			document.getElementById("dlcForm").style.display="none";
			document.getElementById("consoleForm").style.display="none";
		}
			
		}	
		</script>
		<script>
		function formEsistente(){
		if(document.getElementById("esistenteProdotto").checked){
			document.getElementById("esistente").style.display="block";
			document.getElementById("nuovo").style.display="none";
			document.getElementById("ordine").style.display="none";
			}else if(document.getElementById("nuovoProdotto").checked){
			document.getElementById("nuovo").style.display="block";
			document.getElementById("esistente").style.display="none";
			document.getElementById("ordine").style.display="none";
			}
				
		}
		</script>
		<script>
		function spedizioneProdotto(){
			if(document.getElementById("spedizioneProdotto").checked){
			document.getElementById("ordine").style.display="block";
			document.getElementById("nuovo").style.display="none";
			document.getElementById("esistente").style.display="none";
			}
		}
		
		</script>
</body>
</html>