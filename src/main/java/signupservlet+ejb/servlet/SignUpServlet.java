import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.rmi.*;
import javax.naming.*;

import components.*;


/**
 *  Uses the simple currency converter EJB to convert the amount
 *	parameter from USD to UK pounds.
 *
 *
 *  @author Java Curriculum Development Team
 *
 */

public class SignUpServlet extends HttpServlet {

		private SignUpHome signUpHome = null;
		private String strError = null;
		private String userName = null;
		private String userEmail = null;

	/**
	 *  Initializes the Currency EJB when the servlet
	 *	is loaded into the Web Server.
	 */
	public void init () {

		try{
			Context ctx = new InitialContext();
			Object obj = ctx.lookup("crs472/Signup");
			signUpHome = (SignUpHome) PortableRemoteObject.narrow(obj,SignUpHome.class);
		}catch (Exception e){
			strError = e.toString();
		};
	}

    public void doGet (HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
		String strOut;
		try{
			response.setContentType("text/html");
			SignUp su = signUpHome.create(request.getParameter("name"), request.getParameter("email"));
			strOut = "U Made it!";
		}catch (Exception e){
			strOut = strError + " **** " + e.toString();
		};
			PrintWriter out = response.getWriter();
			out.print("<html><head><title>Exercise 7.1</title></head><body>");
			out.print("Look what the cat brought in: " + strOut );
			out.print("</body><html>");
			out.close();
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}
}