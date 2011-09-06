<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@page import="no.ntnu.tdt4237.helperactions.*"%>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<tags:header title=" - Log In"></tags:header>

<body>
<%

User loggedInUser = Authentication.logIn(request.getParameter("UserName"), request.getParameter("Password"));
if (loggedInUser != null) {
	//user is logged in.
	session.setAttribute(SessionKeys.USER_OBJECT, loggedInUser);
	
	%>
	<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>
	<%
} else {
	if (request.getParameter("Password") != null) {
		%>
		<tags:error errorMsg="You are not logged in"></tags:error>
		<%
	}
	%>
	<tags:loginbox></tags:loginbox>
	<% 
}
%>
</body>
</html>