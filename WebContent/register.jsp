<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@page import="no.ntnu.tdt4237.helperactions.*"%>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<tags:header title=" - Register"></tags:header>


<body>
<%
boolean registerSuccess = false;
boolean register = request.getParameter("Password") !=null; 
if (register) {
	if ( Database.getUser(request.getParameter("UserName")) == null) {
		// The user doesn't exist
		registerSuccess = Database.saveUser(new User(request.getParameter("UserName"), 
								   request.getParameter("Password"), 
								   request.getParameter("Email"),
								   request.getParameter("FirstName"),
								   request.getParameter("LastName")));
	}
}

if (registerSuccess) {
	%>
		<tags:message message="Check your email!"></tags:message>
	<%
}
else
{
	%>
	<div class="LoginBox">
		<form action="register.jsp" method="post">
			<span>First Name:</span><input type="text" name="FirstName"></input><br/>
			<span>Last Name:</span><input type="text" name="LastName"></input><br/>
			<span>Email address:</span><input type="text" name="Email"></input><br/>
			<span>User Name:</span><input type="text" name="UserName"></input><br/>
			<span>Password:</span><input type="password" name="Password"></input><br/>
			<input type="submit" value="Register"></input>
		</form>
	</div>
	<%
}
%>


</body>
</html>