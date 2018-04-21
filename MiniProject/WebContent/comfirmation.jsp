
<%@page import="java.util.ArrayList"%>
<%@page import="model.Reservation"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="script.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comfirmation</title>
</head>
<body>

	<form method="post" autocomplete="off" action="/MiniProject/ResServlet">
		<div class="autocomplete" style="width: 300px;">
			<input id="book" type="text" name="book"
				placeholder="Book name">
		</div>
		<input type="submit">
	</form>
	
<jsp:useBean id="listBook" type ="ArrayList<model.Book>" scope="request" />
	<%String affiche=Reservation.print(listBook);
	String tab[]=Reservation.getTabBook(listBook);
	String listTab="[";
	for(String e:tab){
		listTab=listTab+"\""+e+"\",";
	}
	listTab=listTab.substring(0,listTab.length()-1);
	listTab=listTab+"]";%>
	<script>
	var books =<%=listTab%> ;
autocomplete(document.getElementById("book"), books);
</script>
</body>
	<jsp:useBean id="name" type ="java.lang.String" scope="request" />
	<p><%=name %> a bien ete reserve</p>
	<form action="/MiniProject/ResServlet" method="POST" >
		<input id="target" name="target" value="welcome" type="hidden"></input>
		<input id="button" type="submit" value="Retour sur la page welcome"></input>
	</form>
</body>
</html>