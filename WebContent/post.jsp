<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@ page import="no.ntnu.tdt4237.helperactions.*" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="java.util.Date"%><html xmlns="http://www.w3.org/1999/xhtml">

<tags:header title=" - Post"></tags:header>

<body>

<%
User loggedInUser = (User) session.getAttribute(SessionKeys.USER_OBJECT);
String blogOwnerName = StringHelpers.getPostOwnerName(request.getRequestURL().toString());
User postOwner = Database.getUser(blogOwnerName);
%>
<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>

<%
if (postOwner!=null) 
{
	String postDate = StringHelpers.getPostDate(request.getRequestURL().toString(), postOwner.getUserName());
	String postTitle = StringHelpers.getPostTitle(request.getRequestURL().toString(), postOwner.getUserName());
	BlogPost post = Database.getPost(postOwner, postTitle, DateHelpers.parseString(postDate));
	
	if (post!=null) 
	{
		//process delete
		if (request.getParameter("Delete") !=null)
		{
			Database.deletePost(post);
			%>
			<tags:message message="The post is deleted."></tags:message>
			<%
		}
		else
		{
		
			//process new comment
			if (loggedInUser != null && request.getParameter("text") != null)
			{
				
				Database.saveComment(new Comment(loggedInUser, (String) request.getParameter("text"), new Date(), post));
			}
			else if (request.getParameter("DeleteComment") != null)
			{
				User userOfComment = Database.getUser(request.getParameter("dOwner"));
				Comment comment = new Comment(userOfComment, (String) request.getParameter("dText"),
						DateHelpers.parseString(request.getParameter("dDate")), post);		
				Database.deleteComment(comment);
			}
			
			%>
			<tags:blogpost post="<%=post %>" loggedInUser="<%=loggedInUser %>" full="true">
			</tags:blogpost> 
			<%
			boolean isBlogOwner = post.getOwner().equals(loggedInUser);
			for(Comment com : Database.getPostComments(post))
			{
				%>
				<tags:comment comment="<%=com%>" showDelete="<%=isBlogOwner%>"></tags:comment> 
				<%
			}
			
			if (loggedInUser != null)
			{
				%>
				<tags:addcomment></tags:addcomment>
				<%
			}
		}
	}
	else
	{
		%>
		<tags:error errorMsg="This post doesn't exist"></tags:error>
		<%
	}
}
else
{
	%>
	<tags:error errorMsg="This post doesn't exist"></tags:error>
	<%
}

%>
</body>
</html>