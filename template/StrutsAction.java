package actions;

import jabacart.*;

import org.apache.struts.action.*;

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

        // if (!(form instanceof DynaActionForm)) {
		// 	throw new IllegalArgumentException( /* "can't happen" */
		// 		"Input form not a valid DynaForm, configuration error?");
		// }
		// DynaActionForm theForm = (DynaActionForm)form;

		// if (success) {
		// 	messages.add(ActionErrors.GLOBAL_MESSAGE,
		// 		new ActionMessage("success.added.member", member.getName()));
		// 	saveMessages(request, messages);
		// }

		// if (failure) {
		// 	errors.add(ActionErrors.GLOBAL_ERROR,
		// 		new ActionError("error.insert", p.getTitle()));
		// 	saveErrors(request, errors);
		// 	return new ActionForward(mapping.getInput());
		// }

		return mapping.findForward("notwrittenyet");
	}
}
