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
		          <h2  style="color:orange;">Conferma Acquisto</h2>
		          <p>Benvenuto nel tuo Carrello, premi Conferma per procedere con l'acquisto.</p>
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
				 					%><p>NON HAI NESSUN PRODOTTO NEL CARRELLO!</p><%
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
								 							<h4 style="color:orange;"><%=prod.get(j).getNome() %></h4>
								 							<div class="product-info">
									 							<div>Display: <span class="value">5 inch</span></div>
									 							<div>RAM: <span class="value">4GB</span></div>
									 							<div>Memory: <span class="value">32GB</span></div>
									 						</div>
									 					</div>
							 						</div>
							 						<div class="col-md-4 quantity">

							 							<button id="quantity" type="button" value = "1" class="form-control quantity-input btn btn-outline-danger mt-4">elimina</button>
							 						</div>
							 						<div class="col-md-3 price">
							 							<span><%=prod.get(j).getPrezzo() %></span>
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
			 			<div class="col-md-12 col-lg-4">
			 				<div class="summary" style="border-color:orange;background-color:white;">
			 					<h3>COSTO TOTALE</h3>
			 					<div class="summary-item"><span class="text">Costo</span><span class="price">$360</span></div>
			 					<div class="summary-item"><span class="text">Sconto</span><span class="price">$0</span></div>
			 					<div class="summary-item"><span class="text">Totale</span><span class="price">$360</span></div>
			 					<button type="button" class="btn btn-outline-warning btn-lg btn-block">Conferma</button>
				 			</div>
			 			</div>
		 			</div> 
		 		</div>
	 		</div>
		</section>
	</main>
</body>
<!--fine descrizione-->
<%@include file="footer.jsp" %>
</body>
</html>
