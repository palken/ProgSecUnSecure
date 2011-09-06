<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@page import="no.ntnu.tdt4237.helperactions.*"%>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<tags:header></tags:header>

<body>
<%
if (session!=null) 
{
	User loggedInUser = (User) session.getAttribute(SessionKeys.USER_OBJECT);
	if (loggedInUser != null)
	{
		%>
		<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>
		<%
	}
	else
	{
		%> <tags:loginbox></tags:loginbox> <%
	}
}

%>
</body>
</html>