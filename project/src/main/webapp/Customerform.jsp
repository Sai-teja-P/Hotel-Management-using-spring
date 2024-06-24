<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Details of Customer</title>
</head>
<body>
<div class="container">
Please enter your information:
<form:form action="savecustomer" modelAttribute="customerobj">
  Enter name: <form:input path="name"/> <br>
  Enter email: <form:input path="email"/> <br>
  Enter password: <form:input path="password"/> <br>
  Enter mobile number: <form:input path="mobileNumber"/><br>
  Enter address: <form:input path="address"/><br>
<input type="submit" value="Register">
</form:form>
</div>
</body>
</html>