package actions;

import jabacart.*;

import org.apache.struts.action.*;
import com.amazon.soap.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;

/** Dummy Action.
 */
public class DummyAction extends Action {

	public ActionForward execute( 
		ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {

		ServletContext application = getServlet().getServletContext();
		HttpSession session = request.getSession();

		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();

		return mapping.findForward("notwrittenyet");
	}
}
