package reflection.dynamicproxy;

import java.util.ArrayList;
import java.util.List;


/** Our private implementation of the interface */
public class QuoteServerImpl implements QuoteServer {

	final List<String> sayings = new ArrayList<String>();

	QuoteServerImpl() {
		sayings.add("A stitch in time... is better late than never");
		sayings.add("JavaScript is to Java as George Burns is to George Washington.");
		sayings.add("The more old you get, the more you forget");
	}

	public String getQuote() {
		// Get an int in 0..list.size()-1, get that saying from the list.
		return (String)sayings.get((int)(Math.random()*sayings.size()));
	}

	public void addQuote(String newQuote) {
		sayings.add(newQuote);
	}
}
