package cookiecutter;

public class CookieDialogTest {

	public static void main(String[] args) {
		CookieDialog cd = new CookieDialog(null, "Test");
		System.out.println("Setting Visible");
		cd.setVisible(true);
		System.out.println("Dialog done, cookie = " + cd.getCookie());
	}

}
