<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="acquisto.OrdineBean, java.util.ArrayList, acquisto.CorriereEspressoBean"%>
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
	<script src="javascript/controlloSpedizione.js"></script>
	<meta charset="UTF-8">
<title>Ordini da gestire</title>
</head>
<body>

<%
	String visitato= (String)request.getAttribute("visitato");
	if(visitato==null){
		String url="ServletGestioneOrdiniAdmin";
		url= response.encodeURL(url);
		response.sendRedirect(url);
		return;
	}
	
	ArrayList<OrdineBean> ordiniNonSpediti= (ArrayList<OrdineBean>)request.getAttribute("ordiniNonSpediti");
	ArrayList<CorriereEspressoBean> corrieri= (ArrayList<CorriereEspressoBean>)request.getAttribute("corrieri");
%>
	<table>
		<tr>
			<td>Codice ordini</td>
			<td>Email</td>
			<td>Indirizzo</td>
			<td>Data spedizione</td>
			<td>Corriere</td>
		</tr>
		<%
			for(int i=0;i<ordiniNonSpediti.size();i++){
		%>
				<tr>
					<td><%=ordiniNonSpediti.get(i).getCodice() %></td>
					<td><%=ordiniNonSpediti.get(i).getCliente() %></td>
					<td>qua inserisci la data</td>
					<td>qua inserisci il gestore</td>
				</tr>
		<%
			}
		%>
	</table>


</body>
</html>