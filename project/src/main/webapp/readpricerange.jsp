<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>
<% Integer customer_id = (Integer) session.getAttribute("customerinfo");
	if(customer_id != null){
%>
	<form action="fetchproductsbetweenpricerange">
		Enter the Maximum price: <input type="number" name="max"/><br>
		Enter the Minimum price: <input type="number" name="min"/><br>
		<input type="submit"/>
	</form>
	<% } else {%>
	
	<h1><a href="Customerlogin.jsp">Please log in first</a></h1>
	
<% } %>
</body>
</html>