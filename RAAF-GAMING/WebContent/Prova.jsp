<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList , it.unisa.model.ClienteBean"%>
    
<%
	ArrayList<ClienteBean> a= (ArrayList<ClienteBean>) request.getAttribute("listaClienti");//ottengo le righe del db
	if(a==null){
		response.sendRedirect("./servletprova");//cedo il controllo alla Sevlet 
		return ;
	}


%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Prova stampa righe DB</title>
</head>
<body>
	<h1>RISULTATO</h1>
	<%
	
		int i=0;
		while(i<a.size()){
			ClienteBean c= a.get(i);
	%>
			<%= c.getEmail() %>
			<%=c.getNome() %>
			<%=c.getCognome() %>
			<%=c.getPassword() %>
			<%=c.getIban() %>
			<%=c.getData_di_nascita() %>
			<%=c.getCarta_fedelta() %>
 			<br>
 			<br>
	<%
			i++;
		}
	%>
</body>
</html>