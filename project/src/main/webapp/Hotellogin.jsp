<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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
    	justify-content: flex-end; /* Center the form horizontally */
    	align-items: ; /* Center the form vertically */
    	height: 100vh; /* Full viewport height */
    	margin: 0; /* Remove default margin */
    	background-color:gray; 
    }
	
	/* Example for center-aligning form elements */
	form {
		font-family: sans-serif;
	    display: flex;
    	flex-direction: column; /* Align form elements vertically */
    	width: 25%; /* Full width of the container */
    	min-width: 350px;
    	padding: 150px;
    	font-size: 20px;
	}
	input{
            padding: 10px;
            border-radius: 20px; /* Set the border radius to make it rounded */
            border: 1px solid #ccc; /* Add a border for visual appeal */
            
    }
    input[type=submit] {
    		border: none;
            border-radius: 20px; /* Set the border radius to make it rounded */
            border: 1px solid #ccc; /* Add a border for visual appeal */
            color: black;
            background-color: lightblue;
            font-size: 20px;
     }
     .options{
     	align-self: center;
     	text-align: center;
     	color:gray;
     }
     .bar{
     	background-color: black;
     	width: 100%;
     	height: 5px;
     	margin: 20px 0;
     }
</style>
</head>
<body>
<div class="heading">Hotel Login</div>
<h1>${message}</h1>
<form action="hotelloginvalidate" method="post">
	Enter email:<input type="email" name="email"><br>
	Enter password:<input type="password" name="password"><br>
	<input type="submit" value="Login">
	 <div class="bar"></div>
	<div class="options"><a href="addhotel">Sign up</a></div>
</form>
</body>
</html>
<!--  Hotel discount feature not working. -->