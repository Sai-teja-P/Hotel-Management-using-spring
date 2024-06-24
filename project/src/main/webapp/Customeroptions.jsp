<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer options</title>
<style>
.heading{
		font-size: 40px;
		font-family: fantasy;
		text-align: center;
		margin-top: 20px;
		width: 100%;
		position: absolute;
		top: 0;
	}
	body {
		display: flex;
    	justify-content: center; /* Center the form horizontally */
    	align-items: center; /* Center the form vertically */
    	height: 100vh; /* Full viewport height */
    	margin: 0; /* Remove default margin */
    	background-color:grey; 
    	flex-direction: column;
    }
    .container{
		height: 45%; /* Set the height of the square */
		width: 35%;
		max-width: 450px;
		border-radius: 20px; /* Set the rounded corners */
		background-color: lightblue; /* Background color of the square */
     	display: flex;
     	flex-direction: column; /* Stack children vertically */
     	justify-content: center;
     	align-items: center;
     	
     }
     
     button{
     	    font-size: 20px;
     		padding: 10px;
            border-radius: 20px; /* Set the border radius to make it rounded */
            border: 1px solid #ccc;
            margin: 10px;
            color: black;
            text-decoration: none;
     }
     button:hover {
		background-color: lightgray; /* Background color on hover */
		color: darkblue; /* Text color on hover */
	}
    
</style>
</head>
<body>

	<% Integer customer_id = (Integer) session.getAttribute("customerinfo");
	if(customer_id != null){
	%>
	<div class="heading">Customer options: </div><br>
	<h1>${message}</h1>
	<div class="container">
	<a href="fetchapprovedhotelsforcustomer" class="button">View Products by Hotel.</a>
	
	<a href="fetchallproducts" class="button">Buy Products.</a>
	
	<a href="readpricerange.jsp" class="button">Buy Products based on price.</a>
	
	<!-- <a href=""> View Previous orders </a> -->
	</div>
	<% } else { %>
	<div class="container">
		<h1><a href="Customerlogin.jsp">Please log in first</a></h1>
	</div>
<% } %>
</body>
</html> 