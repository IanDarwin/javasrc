package reflection.dynamicproxy;

/** The interface that the impl and the proxy both implement. */
public interface QuoteServer {
	public String getQuote();
	public void addQuote(String newQuote);
}

