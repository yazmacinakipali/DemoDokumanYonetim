<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<title>OrnekBitirme</title>
</head>
<body>
<%

HttpSession sessionsa = request.getSession();
String username = (String) sessionsa.getAttribute("username");
if(username!=null && username !="")
	response.sendRedirect("baskan.jsp");
%>
<form action="LoginServlet" method="post">
<br></br><br></br>
<div id="ogrenciGiris" align="center" class="container">
<h1 align="center">DOKUMAN YONETIM SISTEMI GIRIS PANELI</h1>
Username:<input type="text" name="username" ></input><br></br>
Password:<input type="password" name="password" ></input><br></br>
<input type="submit" name="normalgiris" class="btn btn-info" value=" GIRIS YAP"></input> <a href="register.jsp">Sign Up</a>
<br></br>


</form>
</body>
</html>