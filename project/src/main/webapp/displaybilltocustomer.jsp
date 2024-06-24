<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ page import= "java.util.List" %>
    <%@ page import = "com.entities.FoodOrder" %>
    <%@ page import = "com.entities.Item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${message}</h1>
	<% FoodOrder foodOrder =  (FoodOrder)session.getAttribute("foodOrderInfo");
	 if(foodOrder != null){
	 %>
	 <h1>Delivery Details:</h1>
	 Order ID: <%= foodOrder.getId() %>
	 Name : <%= foodOrder.getName() %>
	 Mobile Number : <%= foodOrder.getMobilenumber() %>
	 Address : <%= foodOrder.getAddress() %>
	 
	 Kindly verify the above details before ordering.
	 <h1>Order Details:</h1>
	 <% List<Item> items = foodOrder.getItems(); %>
	 <table cellPadding="20px" border="1">
	 	<th>name</th>
	 	<th>type</th>
	 	<th>quantity</th>
	 	<th>cost</th>
	 	
	 <% for(Item i:items){ %>
	 		<tr>
	 			<td><%= i.getName()%></td>
	 			<td><%= i.getType() %></td>
	 			<td><%= i.getQuantity() %></td>
	 			<td><%= i.getCost() %></td>
	 		</tr>		 
	 <% } %>
	 </table>
	 <button><a href="">Pay Bill</a></button>
	 <% } 
	 else{
	 %>
	 <button><a href="Customeroptions">Please add any items to order!</a></button>
	 <% 
	 }
	 %>
</body>
</html>