<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.bitirme.islemler.DokumanControl"%>
<%@ page import="com.bitirme.islemler.UserControl"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>DOKUMAN - PROFILIM</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-outline-warning"href="logout.jsp">CIKIS</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-outline-success" href="homepage.jsp">ANASAYFA</a><br></br><br></br>
<%

HttpSession sessionsa = request.getSession();
String username = (String) sessionsa.getAttribute("username");
System.out.print(username);
%>
<%
if(username==null || username==""){
	response.sendRedirect("login.jsp");
}

boolean yetkiKontrolPage  ;
yetkiKontrolPage= com.bitirme.islemler.DokumanControl.YetkiKontrol(username);
System.out.println("YETKIKONTROLVALUE=="+yetkiKontrolPage);
System.out.print(username);

%>	

<div align="center">
		     <table id="hakemTable" class="table table-dark">
                  <tr id="thDiv">
                  <thead  class="thead-dark">                
                  <th>ID</th>
                  <th>DOKUMAN ADI</th>
                  <th>OGRENCI</th>
                  <th>HAKEM-1</th>
                  <th>HAKEM-2</th>
                  <th>HAKEM-3</th>
                  <th>HAKEM-1 PUAN</th>
                  <th>HAKEM-2 PUAN</th>
                  <th>HAKEM-3 PUAN</th>
                  <th>KARAR DURUMU</th>
                  </thead>
                  </tr>
		<%
		String hakem1="",hakem2="",hakem3="",hakem1puan="",hakem2puan="",hakem3puan="",dokumanname="",ogrenciAdi="",onaydurumu="";
		String strQuery = "SELECT * FROM dokumanlar where  dokumanuser='"+username+"' ";
		
		
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekbitirme?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "MyNewPass");
            PreparedStatement ps = conn.prepareStatement(strQuery);
            ResultSet rs = ps.executeQuery() ;
            
            int status;
            String getId;
                while (rs.next())
                {
                  String taskId = rs.getString("dokumanid");
                  ogrenciAdi=rs.getString("dokumanuser");
 				  hakem1 = rs.getString("hakem1");
 				  hakem2 = rs.getString("hakem2");
 				  hakem3=  rs.getString("hakem3");
 				  hakem1puan = rs.getString("hakem1puan");
 				  hakem2puan = rs.getString("hakem2puan");
 				  hakem3puan = rs.getString("hakem3puan");
 				  dokumanname = rs.getString("dokumanname");
                  onaydurumu = rs.getString("kabul");

                  if(hakem1puan=="null" || hakem1puan==null){
                	  hakem1puan="GIRILMEDI";
                  }
                  if(hakem2puan=="null" || hakem2puan==null){
                	  hakem2puan="GIRILMEDI";
                  }
                  if(hakem3puan=="null" || hakem3puan==null){
                	  hakem3puan="GIRILMEDI";
                  }
                  if(onaydurumu=="null" || onaydurumu==null){
                	  if(hakem3puan=="GIRILMEDI" || hakem2puan=="GIRILMEDI" || hakem1puan=="GIRILMEDI"){
                		  onaydurumu="HAKEM HEYETINDE";
                	  }
                	  else{
                		  onaydurumu="OKB KARAR ASAMASINDA";
                	  }
                	  
                    }
 				  %>
                        
              	  <script type="text/javascript">
              	  function getSelected(taskId){
              		  var kdId=taskId+"kabul";
              		  var redId=taskId+"red";
              		  var ogrenciId=taskId+"ogrenci";
              		  console.log(kdId+"***"+redId+"****"+document.getElementById(ogrenciId).innerHTML);
              		if(document.getElementById(kdId).checked){
              			document.getElementById("degerlendirme").value="KABUL";
              			document.getElementById("dokumanid").value=taskId;
              			document.getElementById("ogrenciSonuc").value=document.getElementById(ogrenciId).innerHTML;
              		  }
              		else if(document.getElementById(redId).checked){
              			document.getElementById("degerlendirme").value="RED";
              			document.getElementById("dokumanid").value=taskId;
              			document.getElementById("ogrenciSonuc").value=document.getElementById(ogrenciId).innerHTML;
              		  }
              		kdId="",redId="";
              	  }
              	  </script>
              
                  <tr>
                  <td><%=taskId%></td>
                  <td id="<%=taskId%>dokuman"><%=dokumanname%></td>
                  <td id="<%=taskId%>ogrenci"><%=ogrenciAdi%></td>
                  <td id="hakem1<%=taskId%>"><%=hakem1%></td>
                  <td id="hakem2<%=taskId%>"><%=hakem2%></td>
                  <td id="hakem3<%=taskId%>"><%=hakem3%></td>
                  &nbsp;&nbsp;&nbsp;&nbsp;<td><%=hakem1puan%></td>
                  &nbsp;&nbsp;&nbsp;&nbsp;<td><%=hakem2puan%></td>
                  &nbsp;&nbsp;&nbsp;&nbsp;<td><%=hakem3puan%></td>
             	  <td id="<%=taskId%>onay"><%=onaydurumu%></td>
                  </tr>
                                 	  
          <%
                }
                %>
                </table>
                <% 
        }catch(SQLException e){
                e.printStackTrace();
            }
        %>
        <br></br><br></br><br></br><br></br><br></br><br></br>	
		</div>

		</div>
</body>
</html>