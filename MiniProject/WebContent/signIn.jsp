<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
	<legend><p>Inscrivez vous</p></legend>
	<form method="post" action="/MiniProject/AuthServlet">
		<label>User name <input type="text" name="userName" id="userName" /></label>
		<label>Password  <input type="text" name="passWord" id="passWord" /> </label>
		<input type="hidden" name="target" id="target" value="registrer">
		<input type="submit" value="s'inscrire" />
	</form>
	</fieldset>
</body>
</html>