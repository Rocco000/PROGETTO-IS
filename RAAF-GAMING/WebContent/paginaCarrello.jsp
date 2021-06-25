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
	
	<!-- Css navbar -->
	<link rel="stylesheet" href="css/StileIndex.css" type="text/css">
	
	<!-- script per il carrello -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>

<title>RAAF-GAMING</title>
	<meta charset="utf-8">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	<link rel="stylesheet" href="css/stileCarrello.css">
</head>
<body>
<%
String str = (String) request.getAttribute("visitato");
if(str==null)
{
	str = "servletcarrello";
	String url = request.getRequestURL() + "";
	response.sendRedirect(response.encodeURL(url.replaceAll("paginaCarrello.jsp",str)));
	return;
}
%>
<%@include file="navbar.jsp" %>
<!--inizio descrizione-->
		<main class="page">
	 	<section class="shopping-cart dark">
	 		<div class="container">
		        <div class="block-heading">
		        <%String conferma = (String) request.getAttribute("confermato");
		        if(conferma==null)
		        {
		        	%> <h2  style="color:orange;">Conferma Acquisto</h2>
			          <p>Benvenuto nel tuo Carrello, premi Conferma per procedere con l'acquisto.</p>
		       <% }
		        else
		        {
		        	%><h2  style="color:orange;"><%=conferma %></h2>
		       <%}
		        
		        %>
		         
		        </div>
		        <div class="content">
	 				<div class="row">
	 					<div class="col-md-12 col-lg-8">
	 						<div class="items">
				 				<%
				 				ArrayList<String> carrelloo = (ArrayList<String>) request.getAttribute("carrello");
				 				ArrayList<ProdottoBean> prod = (ArrayList<ProdottoBean>) request.getAttribute("Prodotti");
				 				if(carrelloo==null)
				 				{
				 					%>
				 					<p class="my-5" style="text-align:center; color:orange; font-weight:bold;">NON HAI NESSUN PRODOTTO NEL CARRELLO!
				 					</p>
				 					<%
				 				}
				 				else
				 				{
				 					int j=0;
				 					for(String string : carrelloo)
				 					{
				 						%><div class="product">
				 					<div class="row">
					 					<div class="col-md-3 mt-4 ml-1 mr-1">
					 						<img class="img-fluid mx-auto d-block image" src="servletcard?id=<%=prod.get(j).getCodice_prodotto() %>" style="border-radius:10px;">
					 					</div>
					 					<div class="col-md-8">
					 						<div class="info">
						 						<div class="row">
							 						<div class="col-md-5 product-name">
							 							<div class="product-name">
							 						<%
							 						String urlprodotto = "servletprodotto?id="+prod.get(j).getCodice_prodotto();
							 						urlprodotto = response.encodeURL(urlprodotto);
							 						%>	
								 							<a href="<%=urlprodotto%>" style="color:orange;"><h4 style="color:orange;"><%=prod.get(j).getNome() %></h4></a>
								 							<div class="product-info">
									 							<div>Data Uscita: <span class="value"><%=prod.get(j).getData_uscita() %></span></div>
									 						</div>
									 					</div>
							 						</div>
							 						<div class="col-md-4 quantity">
							 						<%String urlelimina= "servleteliminacarrello?id="+prod.get(j).getCodice_prodotto();
							 						urlelimina = response.encodeURL(urlelimina);%>
													<form  method="post" action="<%=urlelimina%>">
							 							<button id="quantity" type="submit" class="form-control quantity-input btn btn-outline-danger mt-4">elimina</button>
							 						</form>
							 						</div>
							 						<div class="col-md-3 price">
							 							<span><%=prod.get(j).getPrezzo() %>&euro;</span>
							 						</div>
							 					</div>
							 				</div>
					 					</div>
					 				</div>
				 				</div><%
				 				j++;
				 					}
				 				}
				 				%>
				 			</div>
			 			</div>
			 			<div class="col-md-12 col-lg-4" id="#spaziodelcosto">
			 				<div class="summary" style="border-color:orange;background-color:white;">
			 					<h3>COSTO TOTALE</h3>
			 					<% 
			 					if(carrelloo==null){ %>
			 					<div class="summary-item"><span class="text">Totale</span><span class="price">0&euro;</span></div>
			 					
			 					<button type="button" class="btn btn-outline-warning btn-lg btn-block" onclick="noppuoi();">Conferma</button>
			 					<%}
			 					else
			 					{
			 						double cont=0;
			 						int k;
			 						for(k=0;k<prod.size();k++)
			 							cont+=prod.get(k).getPrezzo();
			 						%>
			 					<div class="summary-item mb-3"><span class="text">Totale</span><span class="price"><%=String.format("%.2f",cont) %>&euro;</span></div>
			 					<%
			 					String urldiconferma = "servletconfermaacquisto";
			 					urldiconferma = response.encodeURL(urldiconferma);
			 					%>
			 					<form action="<%=urldiconferma %>" method="post">
			 					<div class="summary-item mb-4"><span class="text mr-4">Indirizzo di consegna:</span><input type="text" name="indirizzodiconsegna" required maxlength="50"></div>
			 					<input type="hidden" name="prezzototale" value="<%=String.format("%.2f",cont).replaceAll(",",".")%>">
			 					<button type="submit" class="btn btn-outline-warning btn-lg btn-block">Conferma</button>
			 					</form>
			 					<%
			 					}
			 					%>
			 					
				 			</div>
			 			</div>
		 			</div> 
		 		</div>
	 		</div>
		</section>
	</main>
	<script>
	function noppuoi()
	{
		alert("Non hai prodotti nel carrello");
	}
	</script>
</body>
<!--fine descrizione-->
<%@include file="footer.jsp" %>
</body>
</html>
