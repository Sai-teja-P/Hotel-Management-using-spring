<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item details</title>
<style>
	body{
		display:flex;
		justify-content: center; /* Center the container horizontally */
		align-items: center; /* Center the container vertically */
		height: 100vh; /* Full viewport height */
		margin: 0; /* Remove default margin */
		background-color: gray;
	}
	.container {
		height: auto; /* Adjust height based on content */
		width: 45%;
		max-width: 550px;
		border-radius: 20px; /* Set the rounded corners */
		background-color: lightblue; /* Background color of the square */
		padding: 20px; /* Add padding inside the container */
	}
	form {
		display: flex;
		flex-direction: column; /* Stack fields vertically */
	}
</style>
</head>
<body>
<div class="container">
Please confirm the item and quantity:
	<form:form action="saveItemtoorder" modelAttribute="itemobj">
		Name: <form:input path="name" readonly="true"/><br>
		Type: <form:input path="type" readonly="true"/><br>
		Cost: <form:input path="cost" readonly="true"/><br>
		Enter Quantity:  <form:input path="quantity"/><br>
		<input type="submit">
	</form:form>
	</div>
</body>
</html>