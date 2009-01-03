package ejb3client;

import ejb3.*;

import javax.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

/**
 * Demonstrate accessing an EJB3 Stateless Session Bean
 */
public class StatelessClient {

	static CardValidatorRemote slsb;

	public static void main(String[] args) throws Exception {
		Context ctx = new InitialContext();
		Object bean = ctx.lookup("StatelessEJB/remote");
		System.out.println("StatelessEJB class = " + bean.getClass());
		slsb = (CardValidatorRemote) PortableRemoteObject.narrow(
			bean, CardValidatorRemote.class);

		System.out.println("Bean = " + slsb);

		test("12345", false);
		test("4567123412341234", true);
	}

	static boolean test(String card, boolean expect) {
		final boolean OK = slsb.validateCard(card);
		System.out.printf("Remote Bean says '%s' is %s valid; this is %s\n",
			card,
			OK ? "" : "not",
			expect == OK ? "expected" : "**UNEXPECTED**");
		return OK;
	}
}
