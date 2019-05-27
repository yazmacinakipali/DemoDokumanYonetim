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
<title>BITIRME ANA SAYFA</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
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
<%
		try{
			 String ilgialani="";
			 ilgialani=UserControl.IlgiAlaniGetir(username);
		}
		catch(SQLException sql){
			sql.printStackTrace();
		}
		finally{
			
		}
		%>		
		<form id="ogrenciForm" action="HakemServlet" method="post">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a  class="btn btn-danger" href="logout.jsp">CIKIS</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
				
		<div id="ogrenciDiv" align="center">
		<%
		 String ilgialani="";
		try{
			 ilgialani=UserControl.IlgiAlaniGetir(username);
			 session.setAttribute("ilgialani", ilgialani);
		}
		catch(SQLException sql){
			sql.printStackTrace();
		}
		finally{
			
		}
		%>	
		<h1 align="center">OGRENCI DOKUMAN</h1>
		<br></br>
		<a  class="btn btn-outline-success" href="profil.jsp">BENIM DOKUMANLARIM</a><br></br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" id="girdiDokumanName1" name="girdiDokumanName" placeholder="Dokuman Adini Giriniz"></input> <br></br>
		<!-- ILGI ALANI METHODDAN CEKİLECEK -->
		Username :<input type="text" name="username" disabled="disabled" value="<%=username%>"></input><br></br>
		Ilgi Alani :<input type="text" name="ilgialani" disabled="disabled" value="<%=ilgialani%>"></input><br></br>		
		<input type="submit" name="ogrenciclick" value="DOKUMAN EKLE" ></input><br></br>
		<!-- DOKUMAN EKLERKEN OGRENCIYE HAKEM ATAMASI ICIN HAKEMLERI LISTELE ILGI ALANI SARTA KOY ALINAN LISTEDEN RANDOM SEC -->
		</div>
		
		
		<div id="hakem">
				<label>*Degerlendirirken notu sectiginize emin olun!</label>
		     <table id="hakemTable" class="table table-dark">
                  <tr id="thDiv">
                  <thead  class="thead-dark">
                  <th scope="col">ID</th>
                  <th scope="col">DOKUMAN ADI</th>
                  <th scope="col">OGRENCI ADI</th>
                  <th scope="col">HAKEM-1</th>
                  <th scope="col">HAKEM-2</th>
                  <th scope="col">HAKEM-3</th>
                  <th scope="col">HAKEM-1 PUAN</th>
                  <th scope="col">HAKEM-2 PUAN</th>
                  <th scope="col">HAKEM-3 PUAN</th>
                  <th scope="col">VERILECEK NOT</th>
                  </thead>
                  </tr>
		<%
		String hakem1="",hakem2="",hakem3="",hakem1puan="",hakem2puan="",hakem3puan="",dokumanname="",ogrenciAdi="";
		String strQuery = "SELECT * FROM dokumanlar where hakem1='"+username+"' or hakem2='"+username+"' or hakem3='"+username+"' and sonuc='hayir' ";
		
		
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
                  %>
                        
                  <script type="text/javascript">
                  		var rateValue="";
						function getSelected(taskId){
							  var rId1=taskId+"1",rId2=taskId+"2",rId3=taskId+"3",rId4=taskId+"4",rId5=taskId+"5",dokumanId=taskId+"dokuman",dokumanValue="";
							  var hakem1="hakem1"+taskId,hakem2="hakem2"+taskId,hakem3="hakem3"+taskId;
							  dokumanValue=document.getElementById(dokumanId).innerText;
							  console.log("dId==="+dokumanId);
							  console.log("dV=="+dokumanValue);
							  console.log(rId1+rId2+rId3+rId4+rId5+"----------------");
			                  console.log("taskid"+taskId);
							if (document.getElementById(rId1).checked) {
								  rate_value = document.getElementById(rId1).value;
								}
								if(document.getElementById(rId2).checked) {
								  rate_value = document.getElementById(rId2).value;
								}
							if (document.getElementById(rId3).checked) {
								  rate_value = document.getElementById(rId3).value;
								}
							if (document.getElementById(rId4).checked) {
								  rate_value = document.getElementById(rId4).value;
								}
							if (document.getElementById(rId5).checked) {
								  rate_value = document.getElementById(rId5).value;
								}							
							document.getElementById("rate_value").value=rate_value;
							document.getElementById("taskId").value=taskId;
							document.getElementById("dokumandegisiklik").value=dokumanValue;
							console.log("rt="+rate_value);
							console.log(taskId);
							document.getElementById('hakem1isim').value=document.getElementById(hakem1).innerHTML;
							document.getElementById('hakem2isim').value=document.getElementById(hakem2).innerHTML;
							document.getElementById('hakem3isim').value=document.getElementById(hakem3).innerHTML;
						}
				  </script>
                  <tr>
                  <td><%=taskId%></td>
                  <td id="<%=taskId%>dokuman"><%=dokumanname%></td>
                  <td id="<%=taskId%><%=ogrenciAdi%>"><%=ogrenciAdi%></td>
                  <td id="hakem1<%=taskId%>"><%=hakem1%></td>
                  <td id="hakem2<%=taskId%>"><%=hakem2%></td>
                  <td id="hakem3<%=taskId%>"><%=hakem3%></td>
                  <%
                  if(hakem1puan=="null" || hakem1puan==null){
                	  hakem1puan="GIRILMEDI";
                  }
                  if(hakem2puan=="null" || hakem2puan==null){
                	  hakem2puan="GIRILMEDI";
                  }
                  if(hakem3puan=="null" || hakem3puan==null){
                	  hakem3puan="GIRILMEDI";
                  }
                  %>
                  &nbsp;&nbsp;&nbsp;&nbsp;<td><%=hakem1puan%></td>
                  &nbsp;&nbsp;&nbsp;&nbsp;<td><%=hakem2puan%></td>
                  &nbsp;&nbsp;&nbsp;&nbsp;<td><%=hakem3puan%></td>
                  <td>NOT:
                  <input type="radio" id="<%=taskId%>1" name="status" value="1">1</input>
                  <input type="radio" id="<%=taskId%>2" name="status" value="2">2</input>
                  <input type="radio" id="<%=taskId%>3" name="status" value="3">3</input>
                  <input type="radio" id="<%=taskId%>4" name="status" value="4">4</input>
                  <input type="radio" id="<%=taskId%>5" name="status" value="5">5</input>
                  </td>
                  <td><input type="button" id="btnApprove" class="btn btn-success" value="DEGERLENDIR" onclick="getSelected(<%=taskId%>);"/>
                  </td>
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
        
		</div>
		  
		<div id="sonuclandirmaDiv" align="center">
        DOKUMAN ID	:  <input type="text"  id="taskId" name="taskId" readonly="true"  value=""></input><br></br>
        DOKUMAN ADI :  <input type="text"  id="dokumandegisiklik" readonly="true"  value=""></input><br></br> 	
        KULLANICI ADI :<input type="text"  id="username" readonly="true"  value="<%=username%>"></input><br></br>	  
		VERILEN NOT   :<input type="text"  name="rate_valuename" id="rate_value" readonly="true" value="0"></input><br></br>
		
		<input type="hidden" readonly="readonly" id="hakem1isim" name="hakem1isim" value=""></input>
		<input type="hidden" readonly="readonly" id="hakem2isim" name="hakem2isim" value=""></input>
		<input type="hidden" readonly="readonly" id="hakem3isim" name="hakem3isim" value=""></input>
		
		<input type="submit" name="hakemonayclick"  value="HAKEM ONAY" onclick="console.log('HAKEM ONAYINA GONDERILDI');"></input>		
					      
        </div>
		<c:choose>
		<c:when test="<%=yetkiKontrolPage==true%>">
			<script>
	        document.title = 'HAKEM DOKUMAN KONTROL';
	        document.getElementById("girdiDokumanName1").style.display='none';
			document.getElementById("ogrenciDiv").style.display='none';
			console.log('BITIR');
			</script>
		</c:when>
		<c:when test="<%=yetkiKontrolPage==false%>">
			<script>
        document.title = 'OGRENCI DOKUMAN EKLE';
		document.getElementById("hakem").style.display='none';
		document.getElementById("hakemTable").style.display='none';
		document.getElementById("sonuclandirmaDiv").style.display='none';
   		  </script>
		</c:when>
		<c:otherwise>
		<script type="text/javascript">alert('OTHERWISE');</script>
		</c:otherwise>
		</c:choose>	
				
		
		</form>

			
			
	
</body>
</html>