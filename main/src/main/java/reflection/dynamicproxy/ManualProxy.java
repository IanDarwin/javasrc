package reflection.dynamicproxy;

/**
 * Just to be contrarian, here is a manual proxy.
 * @author Ian Darwin
 */
public class ManualProxy {
	public static void main(String[] args) {
		QuoteServer proxied = new QuoteServerImpl();
		QuoteServer server = new ManualProxyImpl(proxied);
		server.addQuote("To bae or not to bae, that is the question");
		System.out.println(server.getQuote());
	}
	
	/**
	 * Very simple proxy implementation
	 * @author Ian Darwin
	 */
	static class ManualProxyImpl implements QuoteServer {
		private QuoteServer target;

		ManualProxyImpl(QuoteServer target) {
			this.target = target;
		}

		@Override
		public String getQuote() {
			System.out.println("ManualProxy.ManualProxyImpl.getQuote()");
			return target.getQuote();
		}

		@Override
		public void addQuote(String newQuote) {
			System.out.println("ManualProxy.ManualProxyImpl.addQuote()");
			target.addQuote(newQuote);
		}
		
		
	}
}
