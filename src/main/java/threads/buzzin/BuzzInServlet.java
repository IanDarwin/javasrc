import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/** A quiz-show "buzzer" servlet: the first respondant wins the chance
 * to answer the skill-testing question. Correct operation depends on
 * running in a Servlet container that CORRECTLY implements the Servlet
 * spec, that is, a SINGLE INSTANCE of this servlet class exists, and it
 * is run in a thread pool. This class does not implement "SingleThreadModel"
 * so a correct Servlet implementation will use a single instance.
 * <p>
 * If you needed to work differently, you could synchronized on an object
 * stored in the Servlet Application Context, at a slight increased cost
 * in terms of system overhead.
 */
public class BuzzInServlet extends HttpServlet {

	/** This controls the access */
	protected static boolean buzzed = false;
	/** who got the buzz? */
	protected static String winner; 

	/** doGet is called from the contestants web page.
	 * Uses a synchronized code block to ensure that
	 * only one contestant can change the state of "buzzed".
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		boolean igotit = false;


		// Do the synchronized stuff first, and all in one place.
		synchronized(this) {
			if (!buzzed) {
				igotit = buzzed = true;
				winner = request.getRemoteHost() + '/' + request.getRemoteAddr();
			}
	 	}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Thanks for playing</title></head>");
		out.println("<body bgcolor=\"white\">");

		if (igotit) {
			out.println("<b>YOU GOT IT</b>");
			getServletContext().log("BuzzInServlet: WINNER " +
				request.getRemoteUser());
			// TODO - output HTML to play a sound file
		} else {
				out.println("Thanks for playing, " + request.getRemoteAddr());
				out.println(", but " + winner + " buzzed in first");
		}
		out.println("</body></html>");
	}

	/** The Post method is used from an Administrator page (which should
	 * only be installed in the instructor/host's localweb directory). 
	 * Post is used for administrative functions:
	 * 1) to display the winner;
	 * 2) to reset the buzzer for the next question.
	 * <p>
	 * In real life the password would come from a Servlet Parameter
	 * or a configuration file, instead of being hardcoded in an "if".
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (request.getParameter("password").equals("syzzy")) {
			out.println("<html><head><title>Welcome back, host</title><head>");
			out.println("<body bgcolor=\"white\">");
			String command = request.getParameter("command");
			if (command.equals("reset")) {
				// Synchronize what you need, no more, no less.
				synchronized(this) {
					buzzed = false;
				}
				out.println("RESET");
			} else if (command.equals("show")) {
				// Is this unnecessarily paranoid?
				String w;
				synchronized(this) {
					w = winner;
				}
				out.println("<b>Winner is: </b>" + w);
			}
			else {
				out.println("<html><head><title>ERROR</title><head>");
				out.println("<body bgcolor=\"white\">");
				out.println("ERROR: Command " + command + " invalid.");
			}
		} else {
			out.println("<html><head><title>Nice try, but... </title><head>");
			out.println("<body bgcolor=\"white\">");
			out.println(
				"Your paltry attempts to breach security are rebuffed!");
		}
		out.println("</body></html>");
	}
}
