<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="it.unisa.model.VideogiocoBean , java.util.ArrayList,it.unisa.model.ProdottoBean" %>

<%

	String str1 = "servletindex";
	String url1 = response.encodeURL(str1);
%>

<nav class="navbar navbar-dark">
	<div class="d-inline-flex mr-0 pr-0">
  <a class="navbar-brand" href="<%=url1%>"><img src="immagini/logo.png" alt="RAAF-GAMING"></a>
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" style="border-color: white;">
    <span class="navbar-toggler-icon"></span>
  </button>
  </div>
  <div class="d-inline-flex d-flex justify-content-center ml-0 pl-0 mr-0 pl-0">
  <form action="<%= response.encodeURL("servletricerca") %>" method="get" class="form-inline">
  <input class="form-control" type="text" placeholder="cerca" name="ricerca" style="border-radius:15px;background: url(immagini/lente.png) no-repeat scroll 98% 4px; background-size: 23px 23px ; background-color:white; border-color:black; border-width:2px; width:100%; padding-right: 30px;">
  </form>
  </div>
  <div class="d-inline-flex mr-5 ">
	<div class="dropdown mr-4 ml-0 pl-0">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="border-radius: 70px; background-color :rgba(35,35,35,0.8); border-color: #D2D2D2; border-width:2px;">
   		<i class='fas fa-user-astronaut' style='font-size:24px'></i>
  		</button>						
  		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
  		<% ArrayList<String> array = (ArrayList<String>) request.getAttribute("impostazione");
  		ArrayList<String> array2 = (ArrayList<String>) request.getAttribute("impostazione2");
  		int i=0;
  		for(String string : array){
  		%>
  		<a class="dropdown-item" href="<%=response.encodeRedirectURL(array2.get(i)) %>" style="color:white;"><%=string%></a>
  		<% i++;} %>
		</div>
		</div>
		<%
		String carstring = "servletcarrello";
		carstring = response.encodeURL(carstring);
		%>
		  <a href="<%=carstring %>" style="margin-right:10px; margin-top:6px;">
		  <%
		  ArrayList<String> carr = (ArrayList<String>) request.getAttribute("carrello");
		  if(carr==null)
		  {
			  %><i id="sostituisciCarrello" class='fas fa-shopping-cart' style='font-size:27px; color:white;'></i><%
		  }
		  else
		  {
			  %><i class='fa fa-cart-arrow-down' style='font-size:27px; color:white;'></i><%
		  }
		  %>
	
	</a>
		</div>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class=" mr-5 list-inline">
      <li class="nav-item list-inline-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item list-inline-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item list-inline-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item list-inline-item">
        <a class="nav-link" href="#">Link</a>
      </li>
    </ul>
  </div>
</nav>

