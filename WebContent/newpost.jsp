<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@ page import="no.ntnu.tdt4237.helperactions.*" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.MultipartStream"%>
<%@page import="java.util.Map"%><html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="nl.captcha.Captcha" %>

<tags:header title=" - Post"></tags:header>

<body>

<script>
	function validate() {
		var title = document.getElementsByName("Title")[0].value;
		var text = document.getElementsByName("Post")[0].value;
		if (title.search("^[^<>%$]*$") === -1 || text.search("^[^<>%$]*$") === -1) {
			alert("Contains illegal chars! \n Remove the illegal chars (<>^%$) to be able to post");
			document.getElementById("submitPost").style.display = "none";
		} else {
			document.getElementById("submitPost").style.display = "block";
		}
	}
</script>

<%
User loggedInUser = (User) session.getAttribute(SessionKeys.USER_OBJECT);
%>
<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>

<%
	if (loggedInUser != null)
{
	boolean postOk = false;
	Map<String, String[]> parameters = MultiPartParser.parseRequest(null, request, getServletContext(), "UTF-8");
	if (parameters !=null && parameters.containsKey("Title"))
	{
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
		request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars
		String answer = parameters.get("answer")[0];
		if (captcha.isCorrect(answer)) { %>
		    <b>Correct captcha!</b> <br />
		<% } else { %>
		    <b>Wrong captcha!</b> <br />
		<% }
		
		BlogPost post = new BlogPost(parameters.get("Title")[0],
							 parameters.get("Post")[0],
							 new Date(),
							 loggedInUser);
		 
		String[] filename = parameters.get("FileName");
		if (filename != null) 
		{
			post.setPictureName(filename[0]);
		}	
		
		postOk = Database.savePost(post);
	} 
	if (postOk)
	{
%>
		<span>Your post is stored!</span>
		<%
	}
	else
	{
		%>
		

		<div class="blogPost">
			<form action="" method="post" enctype="multipart/form-data">
				<span>Title:</span><input type="text" name="Title"  onblur="validate()"/><br/>
				<span>Date:</span><input type="text" name="Date" disabled="disabled" value="<%=DateHelpers.toShortFormat(new Date())%>"/><br/>
				<span>Picture:</span><input type="file" name="Picture" accept="image/jpeg image/gif image/png"/><br/>
				<span>Post:</span><br/>
				<textarea class="text" rows="5" cols="30" name="Post" onblur="validate()"></textarea>
				<img src="stickyCaptcha.png" /><br />
				<span>Captcha Answer:</span> <input name="answer" /> <br/>
				<input type="submit" id="submitPost" value="Post"/>
			</form>
		</div>
		<%	
	}
		
} 
else
{
	%>
	<span>You have to log in to be able to create a post!</span> 
	<%
}
%>
</body>
</html>