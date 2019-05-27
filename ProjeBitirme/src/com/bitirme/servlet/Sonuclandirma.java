package com.bitirme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitirme.islemler.DokumanControl;

/**
 * Servlet implementation class Sonuclandirma
 */
@WebServlet("/Sonuclandirma")
public class Sonuclandirma extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter out ;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sonuclandirma() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String degerlendirme = request.getParameter("degerlendirme");
		String dokumanid = request.getParameter("dokumanid");
		String ogrenciSonuc = request.getParameter("ogrenciSonuc");
		out=response.getWriter();
		System.out.println("***"+"degerlendirme"+degerlendirme+"DID:"+dokumanid+"OGRENCI"+ogrenciSonuc+"asd");
		if(degerlendirme.equals(null) || degerlendirme.equals("")) {
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('Lutfen onaylanacak dokumani secin!');");
			out.println("location='baskan.jsp';");
			out.println("</script>");
		}
		else {
			try {
				if(DokumanControl.dokumanSonuclandir(dokumanid, degerlendirme)) {
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('SONUCLANDIRMA BASARILI!');");
					out.println("location='baskan.jsp';");
					out.println("</script>");
				}
			} catch (SQLException e) {
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('BAGLANTI HATASI , DAHA SONRA TEKRAR DENEYIN !');");
				out.println("location='baskan.jsp';");
				out.println("</script>");
				e.printStackTrace();
			}
		}
		
		
	}

}
