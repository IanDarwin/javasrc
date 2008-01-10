package jsf;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;

/**
 * Non-realistic example of a JSF Managed Bean
 */
public class ManagedBean {
	/** Normal event handler, used as e.g.,
	 * &lt;h:commandButton value="Go" action="#{managedBean.handle}"/>
	 * @return
	 */
	public String handle() {
		return "next";
	}
	
	/** "Redisplay same page" handler, used as e.g.
	 * &lt;h:commandButton ... actionListener="#{managedBean.invoke}"/>
	 * @param e Description of the action
	 */
	public void invoke(ActionEvent e) {
		final PhaseId phaseId = e.getPhaseId();
		System.out.println("ManagedBean.invoke(): phaseId = " + phaseId);
	}
}
