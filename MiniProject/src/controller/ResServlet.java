package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.Person;
import model.Reservation;

/**
 * Servlet implementation class ResServlet
 */
@WebServlet("/ResServlet")
public class ResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResServlet() {
        super();
       
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
		HttpSession s=request.getSession(true);
		RequestDispatcher dispatcher = null;
		Reservation res=new Reservation();
		String name=request.getParameter("book");
		String reserve=request.getParameter("bookRes");
		String back=request.getParameter("target");
		if(s.getAttribute("userName")!=null) {
			if(back!=null&&back.equals("welcome")) {
				try {
					request.setAttribute("listBook", res.loadBooks());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				dispatcher=getServletContext().getRequestDispatcher("/welcome.jsp");
			}else {

				try {
					request.setAttribute("listBook",res.loadBooks());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if(reserve!=null) {
					try {
						if(res.resBook(reserve,(String) s.getAttribute("userName"))) {
							request.setAttribute("name", reserve);
							dispatcher=getServletContext().getRequestDispatcher("/comfirmation.jsp");
						}else {
							dispatcher=getServletContext().getRequestDispatcher("/error.jsp");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}else {
					String message;
					try {
						Book b=res.getBook(name);
						dispatcher=getServletContext().getRequestDispatcher("/book.jsp");
						if(b!=null) {
							if(b.isReserved()) {
								message="Nous somme desole de vous anoncer que \""+b.getName()+"\" est deja reserve par "+ b.getUser()+
										".";
							}else {
								message="\""+b.getName()+"\" est disponible!"+"<FORM method=\"post\" ACTION=\"/MiniProject/ResServlet\">\r\n" +
										"<input id=\"bookRes\" name=\"bookRes\" type=\"hidden\" value=\""+b.getName()+"\">"+
										"    <INPUT TYPE=\"SUBMIT\" id=\"reserve\" VALUE=\"Reservez le maintenant\">\r\n" + 
										"  </FORM>";
							}
						}else {
							message="Nous ne possedont pas ce livre :(";
						}

						request.setAttribute("message", message);

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			dispatcher=getServletContext().getRequestDispatcher("/error.jsp");
		}
		dispatcher.include(request, response);
	}

}
