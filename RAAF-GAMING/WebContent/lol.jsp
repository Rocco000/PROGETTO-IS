<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.unisa.model.VideogiocoBean , java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	VideogiocoBean video1 = (VideogiocoBean)request.getAttribute("migliorVideogioco");
	VideogiocoBean video2 = (VideogiocoBean)request.getAttribute("ultimoUscito");
	ArrayList<VideogiocoBean> video4 = (ArrayList<VideogiocoBean>)request.getAttribute("scontati");
	VideogiocoBean video3 = video4.get(0);
%>
<p><img src="servletcard?id=<%=video1.getProdotto()%>"></p>
<br>
<br>
<br>
<p><img src="servletcard?id=<%=video2.getProdotto()%>"></p>
<br>
<br>
<p><img src="servletcard?id=<%=video3.getProdotto()%>"></p>
</body>
</html>