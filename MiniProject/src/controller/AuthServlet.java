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

import model.Authentication;
import model.Book;
import model.Person;
import model.Reservation;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
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
		Person user=new Person(request.getParameter("userName"),request.getParameter("passWord"));
		Authentication authentication=new Authentication();
		RequestDispatcher dispatcher = null;
		Reservation res=new Reservation();
		if("logOut".equals(request.getParameter("target"))) {
			if(s.getAttribute("userName")!=null) {
				request.setAttribute("userName", s.getAttribute("userName"));
			}else {
				request.setAttribute("userName", "Sir");
			}
			s.invalidate();
			dispatcher=getServletContext().getRequestDispatcher("/goodbye.jsp");
		}else if("signIn".equals(request.getParameter("target"))) {
			dispatcher=getServletContext().getRequestDispatcher("/signIn.jsp");
		}
		else if("registrer".equals(request.getParameter("target"))) {
			try {
				if(authentication.register(user)) {
					System.out.println("Vous vous etes bien enregistre "+user.getName());
					dispatcher=getServletContext().getRequestDispatcher("/index.jsp");
				}else{
					dispatcher=getServletContext().getRequestDispatcher("/error.jsp");
					System.out.println("Cet utilisateur est deja pris");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
		else {
			try {
				if(authentication.connection(user)) {
					s.setAttribute("userName", user.getName());
					dispatcher=getServletContext().getRequestDispatcher("/welcome.jsp");
					
					//res.initDataBase(); //Cette ligne reinitialise la bdd
					request.setAttribute("listBook",res.loadBooks());

				}else {
					dispatcher=getServletContext().getRequestDispatcher("/error.jsp");
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		dispatcher.include(request, response);
		doGet(request, response);
	}

}
