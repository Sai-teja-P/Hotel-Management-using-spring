<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ page import="com.entities.Admin" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin options</title>
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

	<% 
	Admin admin= (Admin) session.getAttribute("admininfo");
	if(admin!=null) { 
	%>
	<div class="heading">Admin options: </div><br>
	<h1>${message}</h1>
	<div class="container">
	<br>
		<button><a href="fetchunapprovedhotels">Approve Hotel</a></button>
		<br>
		<button><a href="fetchapprovedhotels">Block Hotel</a></button>
		<br>
		<button><a href="fetchblockedhotels">Unblock Hotel</a></button>
		<br>
		<button><a href="providediscount">Provide Discount</a></button> <!--  left to complete implementation -->
		<br>
		</div>
		<%
		} else { 
		%>
		<div class="container">
 		<button><h1><a href="Adminlogin.jsp"> Please Login First </a></h1></button>
 		</div>
 		<% 
 		}
		%>	
</body>
</html>