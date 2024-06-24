<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ page import ="java.util.List" %>
    <%@ page import = "com.entities.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% List<Product> products = (List) request.getAttribute("productsList"); 
		if(!products.isEmpty()){
	%>
	<h1>${message}</h1>
	<table cellPadding="20px" border="1">
		<th>name</th>
		<th>type</th>
		<th>cost</th>
		<th>add</th>
		
		<% for(Product p : products){ %>
			<tr>
				<td> <%=p.getName()%> </td>
				<td> <%=p.getType()%> </td>
				<td> <%=p.getCost()%> </td>
				<td> <a href="additem?id=<%=p.getId()%>">Add</a></td>
			</tr>
			<% } %>
	</table>
	<button><a href="viewaddeditemstocustomer.jsp">Proceed to buy</a></button>
	<button><a href="fetchapprovedhotelsforcustomer">Back to menu</a></button>
	<% }else{
		%>
		<h1>No Hotel found with the given name <br> Please try another name.</h1>
	<% } %>	
</body>
</html>