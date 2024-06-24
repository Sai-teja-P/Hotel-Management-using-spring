<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product information</title>
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
Please enter the product information:
	<form:form action ="saveproduct" modelAttribute="productobj">
		Enter name: <form:input path="name" /><br>
		Enter type: <form:input path="type" /><br>
		Enter cost: <form:input path="cost" /><br>
		Enter discount: <form:input path="discount" /><br>
		<input type="submit">
	</form:form>
	</div>
</body>
</html>
