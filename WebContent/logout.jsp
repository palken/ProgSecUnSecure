<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@page import="no.ntnu.tdt4237.helperactions.*"%>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
session.removeAttribute(SessionKeys.USER_OBJECT);
session.invalidate();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<tags:header title=" - Log Out"></tags:header>

<body>
<tags:menu></tags:menu>
<tags:message message="You are now logged out"></tags:message>
</body>
</html>