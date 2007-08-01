package reflection.showlet;
// package showoff;

public class ShowletProxy implements Showlet {
	Showlet realObject;

	public ShowletProxy(Showlet userProvidedShowlet) {
		this.realObject = userProvidedShowlet;
	}

	public void show() {
		final String className = realObject.getClass().getName();
        if (securityOK()) {
			log("About to call " + className);
        	realObject.show();
        	log("Completed " + className);
        } else {
        	log("Security - rejected " + className);
        }
	}

	private void log(String string) {
		System.out.println(string);
	}

	private boolean securityOK() {
		return true;
	}
}

