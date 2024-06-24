<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ page import="java.util.List" %>
    <%@ page import="com.entities.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>
<% List<Product> products = (List) request.getAttribute("products"); %>
<table cellPadding="20px" border="1">
	<th>id</th>
	<th>name</th>
	<th>type</th>
	<th>cost</th>
	<th>discount</th>
	<th>update</th>
	<th>delete</th>
	<%
	for(Product p : products) {
	%>
	<tr>
		<td><%=p.getId() %></td>
		<td><%=p.getName() %></td>
		<td><%=p.getType() %></td>
		<td><%=p.getCost() %></td>
		<td><%=p.getDiscount() %></td>
		<td><a href="updateproduct?id=<%=p.getId()%>">Update</a></td>
		<td><a href="deleteproduct?id=<%=p.getId()%>">Delete</a></td>
	</tr>
	<%
	}
	%>
</table>
<a href="Hoteloptions.jsp">Back to main menu</a> <br> <a href="hotellogout">Logout</a>

</body>
</html>