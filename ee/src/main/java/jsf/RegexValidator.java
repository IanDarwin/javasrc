package jsf;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * A generic Regex-based validator for JSF.
 * Must be defined in faces-config like so:
 * <pre>
 *  &lt;validator>
 *    &lt;validator-id>regexValidator&lt;/validator-id>
 *    &lt;validator-class>com.darwinsys.jsf.RegexValidator&lt;/validator-class>
 *    &lt;attribute>
 *      &lt;attribute-name>pattern&lt;/attribute-name>
 *      &lt;attribute-class>java.lang.String&lt;/attribute-class>
 *    &lt;/attribute>
 * &lt;/validator>
 * </pre>
 * Can then be used in your JSF like so:
 * <pre>
 * &lt;h:inputText id="telNo" value="#{customer.phone}" required="true">
 * &lt;f:validator validatorId="regexValidator">
 * 	&lt;f:param name="pattern" value=".*"/>
 * &lt;/h:inputText>
 * </pre>
 */
public class RegexValidator implements Validator {

	private String pattern;
	private Pattern p;

	public void validate(FacesContext ctx, 
			UIComponent comp, Object o)
			throws ValidatorException {
		System.out.printf("RegexValidator.validate(%s)%n", o.getClass());
		String s = o.toString();
		if (!p.matcher(s).matches()) {
			throw new ValidatorException(
				new FacesMessage(
						String.format("%s does not match %s", s, pattern)));
		}
		// else OK
	}
	
	public void setPattern(String patt) {
		if (patt == null) {
			throw new ValidatorException(
					new FacesMessage("pattern is required for regex validator"));
		}
		this.pattern = patt;
		p = Pattern.compile(pattern);
	}
}
