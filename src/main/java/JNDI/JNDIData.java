import java.rmi.*;
import java.io.*;

public class TData implements Remote, Serializable {
	protected String message;
	public void setMessage(String m) {
	 message = m;
	}
	public String getMessage() {
			return message;
	}
}