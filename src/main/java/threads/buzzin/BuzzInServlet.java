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
public class BuzzInServlet {

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
		synchronized(this) {
			if (!buzzed) {
				igotit = buzzed = true;
				winner = request.getRemoteUser();
			}
	 	}

		PrintWriter out = response.getWriter();

		if (igotit) {
			out.println("YOU WIN");
			getServletContext().log("BuzzInServlet: WINNER " +
				request.getRemoteUser());
			// TODO - output HTML to play a sound file
		} else {
				out.println("Thanks for playing, " + request.getRemoteUser());
				out.println(", but " + winner + " buzzed in first");
		}
	}

	/** The Post method is used from an Administrator page (only in the
	 * instructor/host's localweb directory). It is used to reset the
	 * buzzer for the next question.
	 * <p>
	 * In real life the password would come from a Servlet Parameter
	 * or a configuration file.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		if (request.getParameter("hosthost").equals("syzzy")) {
			synchronized(this) {
				buzzed = false;
			}
			out.println("RESET");
		} else {
			out.println(
				"Your paltry attempts to breach security are rebuffed!");
		}
	}
}
