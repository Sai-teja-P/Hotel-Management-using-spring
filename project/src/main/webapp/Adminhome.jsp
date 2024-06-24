<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
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

	<div class="heading">Admin Home </div><br>
	<h1> ${message}</h1>
	<div class="container">
	<button><a href="addadmin">Register</a></button>
	<br>
	<button><a href="Adminlogin.jsp">Log in</a></button>
	</div>
</body>
</html>