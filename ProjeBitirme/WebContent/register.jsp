<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>BITIRME PROJE</title>
</head>
<%
HttpSession sessionsa = request.getSession();
String username = (String) sessionsa.getAttribute("username");
if(username!=null && username !="")
	response.sendRedirect("homepage.jsp");
%>
<body>
<br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="login.jsp" class="btn btn-info">GIRIS PANELINE DON</a>
<form action="RegisterServlet" method="post">
<div id="registerDiv" align="center" class="container">
<h1 align="center">KULLANICI KAYIT</h1><br></br>
<input type="text"  minlength="6" maxlength="12" name="regisUsername" placeholder="Kullanici Adi" required="required" /><br></br>
<input type="password" minlength="6" maxlength="12" name="regisPsw1"  placeholder="Sifre" required="required"/><br></br>
<input type="password" minlength="6" maxlength="12" name="regisPsw2" placeholder="Tekrar Sifre" required="required"/><br></br>
<label>ILGI ALANI SECINIZ</label><br></br>
<input type="radio" id="ilgialaniRegis1" name="ilgi" value="bilisim" required="">bilisim</input>
<input type="radio" id="ilgialaniRegis2" name="ilgi" value="fizik" required="">fizik</input><br></br>
<input type="tel" id="tel" name="tel" placeholder="Telefon No" minlength="11" maxlength="18" required="required"/><br></br>
<input type="email"  name="regisEmail" maxlength="30" placeholder="Mail" required="required"/><br></br>
<label>YETKI SECINIZ</label><br></br>
<input type="radio" id="yetkihakem" name="yetki" value="hakem" required="">hakem</input>
<input type="radio" id="yetkiogrenci" name="yetki" value="ogrenci" required="">ogrenci</input><br></br>
<!-- BURAYA YETKI ICIN COMBOBOX GELECEK VE SECILI OLAN ALINACAK -->
<input type="submit" class="btn btn-info" value="KAYIT TAMAMLA" />
</div>

</form>
</body>
</html>