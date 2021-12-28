<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="acquisto.OrdineBean, java.util.ArrayList, acquisto.CorriereEspressoBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordini da gestire</title>
</head>
<body>

<%
	String visitato= (String)request.getAttribute("visitato");
	if(visitato==null){
		String url="servletformordiniadmin";
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