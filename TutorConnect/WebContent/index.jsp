<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TutorConnect</title>
<head>
<link rel="stylesheet" type="text/css" href="template.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="jquery.cookie.js"></script>
<script src = "javascript.js"></script>


</head>
<body>
<div id="page">
	<div id="header">
		
			<div id="title">
				<div id= "titleBackground">
					<div id="homebutton">&nbsp;TutorConnect
					</div>
				</div>
				<div id="welcometext">Login
				</div>
			</div>
		
		<div id="aboutcontact">
			<div id="about">&nbsp;About
			</div>
			<div id="contact">&nbsp;ContactUs
			</div>
		</div>
	</div>
	<br/>
	<div id="container">
		<div id= "searchContainer">
			<form name= "searchBox" action="">
				<label for="search"></label>
        		<input type="text" name="search" id="search" placeholder="Type to search..." style="
				    width: 85%;
				    height: 20%;">
        		<input type="submit" value="Search" style="
    				font-size: medium;">
			</form>
		</div>
		<div id="content">&nbsp;
		</div>
		<div id="eventBar">
			
			<div id="addButton" > Add Event
				<div id="addForm">
				<form name="eventForm" action="" style="
				    font-size: medium;
				    text-align: left;
				    padding: 1%;
				    color: black;
					text-shadow: 0px 0px black;">
				    
					Title
					<input style="float:right;" id="Title" type="text" name="Title" placeholder="Title"><br/><br/>
					Location	 
					<input style="float:right;" id="Location" type="text" name="Location" placeholder="Location"><br/><br/>
					Description	
					<input style="float:right;" id="Description" type="text" name="Description" placeholder="Description"><br/><br/>
					Date
					<input style="float:right;" id="Day" type="date" name="Day"><br/><br/>
					Time		 
					<input style="float:right;" id="Time" type="time" name="Time"><br/><br/>
					
					<input type="submit" value="Submit">
				</form>
				</div> 		
			</div>	
			<hr>
			<div id="deleteButton"> Delete Event
				<div id= "deleteForm" >
					<form name= deleteEventForm action="" style="
				    font-size: medium;
				    text-align: left;
				    padding: 1%;
				    color: black;
					text-shadow: 0px 0px black;">
						Event ID
						<input style="float: right;" id= "deleteID" type="text" name="ID" placeholder= "ID"><br/><br/>
						
						<input type="submit" value="Delete">
					</form>
				</div>
			</div>
			<hr>
			<div id="myEvents"> My Events
				
			</div>
			
			
		</div>
	</div>
	<div id= "loginContent"> 
	<div id = "loginFormBox">
		<form name= "loginForm" action="">
				Username (do not use banner credentials) *: 
				<input id="Username" type="text" name="Username" placeholder="username" style="
					float: right; padding-right: 4%" ><br/><br/>
				Password (do not use banner credentials) *: 
				<input id="Password" type="password" name="Password" placeholder="password" style="
					float: right; padding-right: 4%" ><br/><br/>
				
				<input type="submit" value="Login" style="
				    float: right;
				    margin-left: 4%;
				    font-size: medium;
				    color: black;">
			</form>
			<div id="newUserText">If you are a new user, click <span id="newUserLink">here</span> to create a new account!</div>
		</div>
	</div>
	<div id="createLoginContent"> Create a new user account
	<div id = "createLoginFormBox">
		<form name= "createLoginForm" action="">
				Username (Desired username) *: 
				<input id="createUsername" type="text" name="Username" style="
					float: right; padding-right: 4%" ><br/><br/>
				Password (Desired password) *: 
				<input id="createPassword" type="password" name="Password" style="
					float: right; padding-right: 4%" ><br/><br/>
				
				<input type="submit" value="Create Account" style="
				    font-size: medium;
				    float: right; padding-right: 4%;
				    color: black;">
			</form>
			<div id="loginReturnText">Click <span id="returnToLogin">here</span> to return to login form!</div>
		</div>
	</div>
</div>
</body>
</html>