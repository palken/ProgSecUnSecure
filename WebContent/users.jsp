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
User loggedInUser = (User) session.getAttribute(SessionKeys.USER_OBJECT);
%>
<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>
<%
java.util.ArrayList<User> users = Database.getUsers();

for(User u : users)
{
	%>
	<p><a href="blog/<%= u.getUserName() %>"><%= u.getUserName() %></a></p>
	<%
}
%>


</body>
</html>