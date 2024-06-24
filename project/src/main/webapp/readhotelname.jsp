<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="com.entities.Hotel" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="fetchproductsbyhotelname">
		Enter Hotel name: <input type="text" name="hotelname"> <br>
		<input type="submit">  
	</form>
	
	<% List<Hotel> hotels = (List<Hotel>) request.getAttribute("approvedhotels"); %>
	<table cellPadding="20px" border="1">
		<th> Hotels </th>
		
		<% for(Hotel h:hotels){ %>
		<tr> 
			<td>
				<button>
					 <a href="fetchproductsbyhotelname?hotelname=<%=h.getName() %>">
						<%= h.getName() %> 
					</a>
				</button>
			</td>
		</tr>
			
		<% } %>
	</table>

</body>
</html>