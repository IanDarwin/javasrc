// $Id$
public class AutoSave extends Thread {
	FileSaver model;

	public AutoSave(FileSaver m) {
		super("AutoSave Thread");
		setDaemon(true);		// so we don't keep the main app alive
		model = m;
	}

	public void run() {
		while (true) {		// entire run method runs forever.
			try {
				sleep(30*1000);
			} catch (InterruptedException e) {
				// do nothing with it
			}
			if (model.wantAutoSave() && model.hasUnsavedChanges())
				model.saveFile(null);
		}
	}

	// Not shown:
	// 1) saveFile() must now be synchronized.
	// 2) method that shuts down main program be synchronized on *SAME* object
}
