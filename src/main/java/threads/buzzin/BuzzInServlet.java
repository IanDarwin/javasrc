import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/** A quiz-show "buzzer" servlet: the first respondant wins the chance
 * to answer the skill-testing question. 
 * <p>
 * Previous versions of this code used shared static variables, but this
 * is not reliable, since most web engines now use custom class loaders
 * that may load a servlet class more than once.  The "right" way is to 
 * synchronize on an object stored in the Servlet Application Context.
 */
public class BuzzInServlet extends HttpServlet {

	/** The attribute name used throughout. */
	protected final static String WINNER = "buzzin.winner";

	/** doGet is called from the contestants web page.
	 * Uses a synchronized code block to ensure that
	 * only one contestant can change the state of "buzzed".
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		ServletContext application = getServletContext();

		boolean iWon = false;
		String user = request.getRemoteHost() + '@' + request.getRemoteAddr();

		// Do the synchronized stuff first, and all in one place.
		synchronized(application) {
			if (application.getAttribute(WINNER) == null) {
				application.setAttribute(WINNER, user);
				application.log("BuzzInServlet: WINNER " + user);
				iWon = true;
			}
	 	}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Thanks for playing</title></head>");
		out.println("<body bgcolor=\"white\">");

		if (iWon) {
			out.println("<b>YOU GOT IT</b>");
			// TODO - output HTML to play a sound file :-)
		} else {
				out.println("Thanks for playing, " + request.getRemoteAddr());
				out.println(", but " + application.getAttribute(WINNER) + 
					" buzzed in first");
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
		ServletContext application = getServletContext();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (request.getParameter("password").equals("syzzy")) {
			out.println("<html><head><title>Welcome back, host</title><head>");
			out.println("<body bgcolor=\"white\">");
			String command = request.getParameter("command");
			if (command.equals("reset")) {
				// Synchronize what you need, no more, no less.
				synchronized(application) {
					application.setAttribute(WINNER, null);
				}
				out.println("RESET");
			} else if (command.equals("show")) {
				String winner = null;
				synchronized(application) {
					winner = (String)application.getAttribute(WINNER);
				}
				if (winner == null) {
					out.println("<b>No winner yet!</b>");
				} else {
					out.println("<b>Winner is: </b>" + winner);
				}
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
