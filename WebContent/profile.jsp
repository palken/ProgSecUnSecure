<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@ page import="no.ntnu.tdt4237.helperactions.*" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<tags:header title=" - Profile"></tags:header>

<body>
<%
User loggedInUser = (User) session.getAttribute(SessionKeys.USER_OBJECT);
%>
<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>
<%
boolean registerSuccess = false;
boolean register = request.getParameter("Password") !=null; 

if (register) {
		registerSuccess = Database.saveUser(new User(request.getParameter("UserName"), 
								   request.getParameter("Password"), 
								   request.getParameter("Email"),
								   request.getParameter("FirstName"),
								   request.getParameter("LastName")));
}


if (registerSuccess) {
	loggedInUser.setEmail(request.getParameter("Email"));
	loggedInUser.setFirstName(request.getParameter("FirstName"));
	loggedInUser.setLastName(request.getParameter("LastName"));
	loggedInUser.setPassword(request.getParameter("Password"));
	loggedInUser.setUserName(request.getParameter("UserName"));
	%>
		<tags:message message="Changes registered!"></tags:message>
	<%
}
else
{
	String blogOwnerName = StringHelpers.getProfileOwnerName(request.getRequestURL().toString());
	User profileUser = Database.getUser(blogOwnerName);
	if(loggedInUser != null && blogOwnerName.equals(loggedInUser.getUserName()))
	{
	%>
	<div class="ProfileBox">
		<form action="profile.jsp" method="get">
			<span>First Name:</span><input type="text" name="FirstName" value="<%=loggedInUser.getFirstName() %>"></input><br/>
			<span>Last Name:</span><input type="text" name="LastName" value="<%=loggedInUser.getLastName() %>"></input><br/>
			<span>Email address:</span><input type="text" name="Email" value="<%=loggedInUser.getEmail() %>"></input><br/>
			<span>User Name:</span><input type="text" name="UserName" value="<%=loggedInUser.getUserName() %>"></input><br/>
			<span>Password:</span><input type="password" name="Password" value="<%=loggedInUser.getPassword() %>"></input><br/>
			<input type="submit" value="Register"></input>
		</form>
	</div>
	<%
	}
	else if (profileUser != null)
	{
		%>
		<span>First Name: </span><span><%=profileUser.getFirstName() %></span><br/>
		<span>Last Name: </span><span><%=profileUser.getLastName() %></span><br/>
		<span>Email address: </span><span><%=profileUser.getEmail() %></span><br/>
		<span>User Name: </span><span><%=profileUser.getUserName() %></span><br/>
		<span>Password: </span><span><%=profileUser.getPassword() %></span><br/>
		<a href="/blog/<%=profileUser.getUserName()%>">Go to his blog</a>
		<%
	} 
	else
	{
		%>
		<tags:error errorMsg="This user doesn't exist!"></tags:error>
		<%
	}
}
%>
</body>
</html>