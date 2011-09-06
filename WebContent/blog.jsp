<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@ page import="no.ntnu.tdt4237.helperactions.*" %>
     <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<tags:header title=" - Blog"></tags:header>

<body>
<%
User loggedInUser = (User) session.getAttribute(SessionKeys.USER_OBJECT);
%>
<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>
<%

String blogOwnerName = StringHelpers.getBlogOwnerName(request.getRequestURL().toString());
User blogOwner = Database.getUser(blogOwnerName);
if (blogOwner !=null)
{
	java.util.ArrayList<BlogPost> blogPosts  = Database.getPostForUser(blogOwner);
	for(BlogPost post: blogPosts) 
	{
		%>
			<tags:blogpost post="<%=post%>" loggedInUser="<%=loggedInUser%>">
			</tags:blogpost> 
		<%
	}
}
else 
{
	%><tags:error errorMsg="This Blog doens't exist"></tags:error><%
}
%>
</body>
</html>