<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ page import="java.util.List" %>
    <%@ page import="com.entities.Item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% List<Item> items = (List<Item>) session.getAttribute("itemslist");
	if(items != null && !items.isEmpty()){
	%>
	<h1>${message}</h1>
	<table cellPadding="20px" border="1">
		<th> name</th>
		<th> cost </th>
		<th> type </th>
		<th> quantity </th>
		<th> remove </th>
		
		<% for(Item i: items) { %>
			<tr>
				<td> <%= i.getName() %></td>
				<td> <%= i.getCost() %></td>
				<td> <%= i.getType() %></td>
				<td> <%= i.getQuantity() %></td>
				<td> <a href="removeitem?id=<%=i.getId()%>">Remove</a></td>
			</tr>
		<% } %>
		</table>
		<button><a href="addfoodorder">Confirm</a></button>  <br>
	 <%  }else{ %>
	 		<h1>Please Add some items.</h1>
	<% } %>	
	 <button><a href="fetchallproducts">Add more Items</a></button>
</body>
</html>