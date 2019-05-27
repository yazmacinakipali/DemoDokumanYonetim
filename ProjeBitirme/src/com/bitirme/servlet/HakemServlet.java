package com.bitirme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitirme.database.Database;
import com.bitirme.islemler.DokumanControl;

/**
 * Servlet implementation class HakemServlet
 */
@WebServlet("/HakemServlet")
public class HakemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out = null ;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HakemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   HttpSession session = request.getSession();
		   System.out.println("hakemservlet");
		   String action = request.getParameter("action");
		   String username =(String) session.getAttribute("username");
		   String puan = request.getParameter("rate_valuename");
		   String hakem1= request.getParameter("hakem1isim");
		   String hakem2= request.getParameter("hakem2isim");
		   String hakem3= request.getParameter("hakem3isim");
		   String dokumanId= request.getParameter("taskId");
		   System.out.println("p"+puan+"h1:"+hakem1+"taskId:"+dokumanId);
		   System.out.println("hakemservlet--username"+username);
		   if(request.getParameter("hakemonayclick")!=null) {
			   System.out.println("*****HAKEMONAYCLICK*****");
				if(puan==null || puan=="" || puan=="null") {
					out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('GECERLI DEGERLER GIRINIZ!');");
					out.println("location='homepage.jsp';");
					out.println("</script>");
				}
				else {
					if(username.equals(hakem1)) {
						String query = "update dokumanlar set hakem1puan='"+puan+"' where dokumanid='"+Integer.parseInt(dokumanId)+"' " ;
						try {
							if(Database.puanEkleme(query, dokumanId)) {
								out = response.getWriter();
								out.println("<script type=\"text/javascript\">");
							    out.println("alert('Hakem1 guncellendi!');");
								out.println("location='homepage.jsp';");
								out.println("</script>");
							}
							else {
								out = response.getWriter();
								out.println("<script type=\"text/javascript\">");
							    out.println("alert('TEKRAR DENEYIN!');");
								out.println("location='homepage.jsp';");
								out.println("</script>");
							}
						} catch (SQLException e) {
							out = response.getWriter();
							out.println("<script type=\"text/javascript\">");
						    out.println("alert('BAGLANTI HATASI!');");
							out.println("location='homepage.jsp';");
							out.println("</script>");
							e.printStackTrace();
						}
					}
					else if(username.equals(hakem2)) {
						String query = "update dokumanlar set hakem2puan='"+puan+"' where dokumanid='"+Integer.parseInt(dokumanId)+"' " ;
						try {
							if(Database.puanEkleme(query, dokumanId)) {
								out = response.getWriter();
								out.println("<script type=\"text/javascript\">");
							    out.println("alert('Hakem2 guncellendi!');");
								out.println("location='homepage.jsp';");
								out.println("</script>");
							}
							else {
								out = response.getWriter();
								out.println("<script type=\"text/javascript\">");
							    out.println("alert('TEKRAR DENEYIN!');");
								out.println("location='homepage.jsp';");
								out.println("</script>");
							}
						} catch (SQLException e) {
							out = response.getWriter();
							out.println("<script type=\"text/javascript\">");
						    out.println("alert('BAGLANTI HATASI!');");
							out.println("location='homepage.jsp';");
							out.println("</script>");
							e.printStackTrace();
						}
						
					}
					else if(username.equals(hakem3)) {
						String query = "update dokumanlar set hakem3puan='"+puan+"' where dokumanid='"+Integer.parseInt(dokumanId)+"' " ;
						try {
							if(Database.puanEkleme(query, dokumanId)) {
								out = response.getWriter();
								out.println("<script type=\"text/javascript\">");
							    out.println("alert('Hakem2 guncellendi!');");
								out.println("location='homepage.jsp';");
								out.println("</script>");
							}
							else {
								out = response.getWriter();
								out.println("<script type=\"text/javascript\">");
							    out.println("alert('TEKRAR DENEYIN!');");
								out.println("location='homepage.jsp';");
								out.println("</script>");
							}
						} catch (SQLException e) {
							out = response.getWriter();
							out.println("<script type=\"text/javascript\">");
						    out.println("alert('BAGLANTI HATASI!');");
							out.println("location='homepage.jsp';");
							out.println("</script>");
							e.printStackTrace();
						}
					}
					else {
						    out = response.getWriter();
							out.println("<script type=\"text/javascript\">");
						    out.println("alert('HATA TEKRAR DENEYINIZ !!');");
							out.println("location='homepage.jsp';");
							out.println("</script>");					
					}
				}
		   }
		   else if(request.getParameter("ogrenciclick")!=null){
			    
			   	String ilgialani =(String) session.getAttribute("ilgialani");
			   	String girdiDokumanName =request.getParameter("girdiDokumanName");
			   	System.out.println("here--dokumanekle ilgialani,username,girdiDokumanName"+ilgialani+username+girdiDokumanName);
			    try {
					if(DokumanControl.dokumanEkle(ilgialani, username, girdiDokumanName)) {
						out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
					    out.println("alert('DOKUMAN KAYDI BASARILI!');");
						out.println("location='homepage.jsp';");
						out.println("</script>");
					}
					else {
						out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
					    out.println("alert('DOKUMAN KAYDI BAÞARISIZ.LUTFEN KONTROL EDIN!');");
						out.println("location='homepage.jsp';");
						out.println("</script>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('BAGLANTI HATASI!');");
					out.println("location='homepage.jsp';");
					out.println("</script>");
				}
		   }
		   else {
				out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('BIR ADET HATA MEVCUT!');");
				out.println("location='login.jsp';");
				out.println("</script>");
		   }		   
	}	
}
