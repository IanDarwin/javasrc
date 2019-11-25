package ejb3;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/** A very crude demo of a Stateless Session Bean.
 * Card Validation is a good example of a stateless service.
 */
@Local(CardValidatorLocal.class)
@Remote(CardValidatorRemote.class)
@Stateless
public class StatelessEJB implements CardValidatorLocal, CardValidatorRemote {

	public boolean validateCard(String card) {
		if (card == null) {
			throw new NullPointerException();
		}
		if (card.length() == 0) {
			throw new IllegalArgumentException();
		}

		if (card.length() != 16) {
			return false;
		}
		if (card.startsWith("4")) {		// VISA
			return true;
		}
		if (card.startsWith("5")) {		// MC
			return true;
		}
		return false;
	}
} 
