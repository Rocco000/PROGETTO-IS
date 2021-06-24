<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" import="it.unisa.model.AbbonamentoBean, it.unisa.model.DlcBean, it.unisa.model.ConsoleBean"%>

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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"> </script><!-- css loghi -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<!-- css mio -->
<link rel="stylesheet" href="css/StileIndex.css" type="text/css">
<link rel="stylesheet" href="css/stileGioco.css" type="text/css">

<title>RAAF-GAMING</title>
</head>
<body>
<%
String str = (String) request.getAttribute("visitato");
if(str==null)
{
	str = "servletprodotto";
	String url = request.getRequestURL() + "";
	response.sendRedirect(response.encodeURL(url.replaceAll("paginaGioco.jsp",str)));
	return;
}
ProdottoBean prod = (ProdottoBean) request.getAttribute("Prodotto");
String tipo = (String) request.getAttribute("tipo");
Object obj = request.getAttribute(tipo);
%>
<%@include file="navbar.jsp" %>
<!--inizio descrizione-->
<div class="container d-flex justify-content-center" style="background-color:rgba(230,230,230,0.5); height:100%; width:90%;">

	<div class="row w-100" style="width:100%;">
		<div class="col-md-6 mt-2 mb-2">
		
				<img src="servletcard?id=<%=prod.getCodice_prodotto() %>" style="border-radius:15px;height:100%;width:100%;">

		</div>
		<div class="col-md-6">
			<div class="container ml-0 mt-4 mr-5" style="background-color:rgba(240,240,230,0.8);height:50%; width:100%; border-radius:15px;overflow:auto;">
						<div class="row justify-content-md-center">
							<div class="row w-110 pl-1" style="background-color:rgb(35,35,35);width:112%">
										<div class="col-md-6">
												<h2 style="color:red;"><%=prod.getNome() %></h2>
										</div>
										<div class="col-md-6" >
												<span class="star-rating pl-2">
												  <input type="radio" name="rating" value="1" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="2" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="3" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="4" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="5" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="6" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="7" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="8" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="9" onclick="recensione(this);"><i></i>
												  <input type="radio" name="rating" value="10" onclick="recensione(this);"><i></i>
											</span>
										</div>
										</div>
										
										<div class="row">
											<div class="col-xl-12 ml-0 pr-2">
													<%if(tipo=="videogioco")
													{
														VideogiocoBean bean = (VideogiocoBean) obj;
														%><h6>Edizione limitata: <%if(bean.getEdizione_limitata()){%> SI<%} else{%> NO<%} %>,&nbsp;
														Data Uscita: <%=prod.getData_uscita() %>,&nbsp;
														Dimensione: <%=bean.getDimensione() %>, &nbsp;
														Tipo: <%if(bean.getVkey()==null){%>FISICO con <%=bean.getNcd()%>cd.<%} else {%>DIGITALE<%} %>,&nbsp;
														Software House: <%=bean.getSoftware_house() %>&nbsp;
														Sconto: <%=prod.getSconto() %>%&nbsp;
														PEGI: <%=bean.getPegi() %>.</h6>
														<%
														}
													else if(tipo=="console")
													{
														ConsoleBean bean = (ConsoleBean) obj;
														%><h6>Specifica: <%=bean.getSpecifica() %>,&nbsp;
														Data Uscita: <%=prod.getData_uscita() %>,&nbsp;
														Colore: <%=bean.getColore() %>&nbsp;
														Sconto: <%=prod.getSconto() %>%.</h6>
														<%
													} 
													else if(tipo=="abbonamento")
													{
														AbbonamentoBean bean = (AbbonamentoBean) obj;
														%><h6>Durata Abbonamento: <%=bean.getDurata_abbonamento()%>,&nbsp;
														Data Uscita: <%=prod.getData_uscita() %>,&nbsp;
														Sconto: <%=prod.getSconto() %>%</h6>
														<%
													} 
													else if(tipo=="dlc")
													{
														DlcBean bean = (DlcBean) obj;
														%><h6>Descrizione: <%=bean.getDescrizione()%>,&nbsp;
														Data Uscita: <%=prod.getData_uscita() %>,&nbsp;
														Dimensione: <%=bean.getDimensione() %>, &nbsp;
														Sconto: <%=prod.getSconto() %>%</h6>
														<%
													} 
														%>
											</div>
										</div>
										<div class="row">
												<h4 class="col-md-12 pt-5" style="position:relative; bottom:0; left:0;"><%=prod.getPrezzo() %> euro <button style="border:none; background-color:rgba(230,230,230,0);" onclick="aggiungiCarrello();"><i class='fas fa-shopping-cart pl-2' style='font-size:27px; color:black;'></i></button></h4>
										</div>
						
						</div>
			</div>
			<div class="container" style="height:315px;"></div>
		</div>
	</div>
	<!-- row -->
	<script>
	function aggiungiCarrello()
	{
		var x = {id: <%=prod.getCodice_prodotto()%>};
		
		var jisono = JSON.stringify(x);
		
		$.ajax({
			
			type: "POST",
			url: "<%=response.encodeURL("servletaggiungicarrello")%>",
			contentType: "application/json",//tipo di dato che invio
			dataType:"text",//tipo di dati che mi aspetto dal server
			data: encodeURIComponent(jisono),
			async: true,
			success: function (data){
				var obj = JSON.parse(data);
				alert(obj.message);
				$("#sostituisciCarrello").removeClass("fas fa-shopping-cart");
				$("#sostituisciCarrello").addClass( "fa fa-cart-arrow-down");
				},
			error: function(){alert("lol");}
	});
		
	}
	
	function recensione(stella)
	{
		var x = {voto: stella.value, id:<%=prod.getCodice_prodotto()%>};
		var jisono = JSON.stringify(x);
		
	$.ajax({
			
			type: "POST",
			url: "<%=response.encodeURL("servletrecensione")%>",
			contentType: "application/json",//tipo di dato che invio
			dataType:"text",//tipo di dati che mi aspetto dal server
			data: encodeURIComponent(jisono),
			async: true,
			success: function (data){
				var obj = JSON.parse(data);
				alert(obj.recensione);
				},
			error: function(){alert("lol");}
	});

	}
	
	</script>
	<!-- fine -->
</div>

<!--fine descrizione-->
<%@include file="footer.jsp" %>
</body>
</html>