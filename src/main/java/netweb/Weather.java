import java.io.*;
import java.net.*;

/** Connect to a METCAST station and print the results */
public class Weather {
	public static final String request = 
	"(webq (bounding-box 90. -180. -90. 180.)" +
    "  (st_constraint" +
    "    (block_id \"716240\")" +
    "    (st_country_code \"CN\"))" +
    "  (products (METAR (mime-type \"text/plain\")))" +
    "  (products (Forecasts (mime-type \"text/plain\"))))";
	public static final String SERVER =
		"http://zowie.metnet.navy.mil/cgi-bin/oleg/server";

	public static void main(String[] args) throws Exception {

		System.out.println("Setting up URLConnection");

		URL u = new URL(SERVER);

		URLConnection cx = u.openConnection();

		cx.setDoInput(true);
		cx.setDoOutput(true);
		cx.setAllowUserInteraction(false);

		DataOutputStream os =
			new DataOutputStream(cx.getOutputStream());

		System.out.println("Connecting the URLConnection");
		cx.connect();

		System.out.println("Request is:");
		System.out.println(request);

		System.out.println("Sending Request");
		os.writeBytes("query=" + URLEncoder.encode(request));
		os.close();

		System.out.println("Getting the Response");
		
		BufferedReader is = new BufferedReader(
			new InputStreamReader(cx.getInputStream()));

		String line;
		while ((line = is.readLine()) != null)
			System.out.println(line);
		is.close();
	}
}

