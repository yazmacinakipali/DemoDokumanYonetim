package com.bitirme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitirme.islemler.UserControl;
import com.bitirme.pojo.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	PrintWriter out = null ;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pw1="",pw2="",username="",email="",yetki="",tel="",ilgialani="";
		pw1=request.getParameter("regisPsw1");
		pw2=request.getParameter("regisPsw2");
		username=request.getParameter("regisUsername");
		email=request.getParameter("regisEmail");
		yetki=request.getParameter("yetki");
		tel=request.getParameter("tel");
		ilgialani =request.getParameter("ilgi");
		User user = new User(username,pw1,email,tel,yetki,ilgialani);
		if(pw1.equals(pw2)) {
			try {
				if(UserControl.UserAdding(user)) {
					out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('Kayit basarili!');");
					out.println("location='register.jsp';");
					out.println("</script>");
				}
			} catch (SQLException e) {
				out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('Kayit basarisiz.BAGLANTI HATASI!');");
				out.println("location='register.jsp';");
				out.println("</script>");
				e.printStackTrace();
			}
		}
		else {
			out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('Sifreler eslesmiyor,yeniden deneyin!');");
			out.println("location='register.jsp';");
			out.println("</script>");
		}
		

	}

}
