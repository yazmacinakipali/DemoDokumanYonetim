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

import com.bitirme.islemler.UserControl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter out ;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		out = response.getWriter();

		HttpSession session = request.getSession(); ;
		
			try {
				if(UserControl.baskanKontrol(username, password)) {
					session.setAttribute("okbyetki", "okbyetki");
					session.setAttribute("username", username);
					response.sendRedirect("baskan.jsp");
				}
				else {
					if(UserControl.UserMatching(username,password)) {
						session.setAttribute("username", username);
						response.sendRedirect("homepage.jsp");
					}
					else {
						   out.println("<script type=\"text/javascript\">");
						   out.println("alert('Giris basarisiz!');");
						   out.println("location='login.jsp';");
						   out.println("</script>");
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
