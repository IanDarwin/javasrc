package netweb;

import java.io.*;
import java.net.*;

import com.darwinsys.io.FileIO;

/** Connect to a METCAST station and print the results
 */
public class Weather {

	public static final String request = 
	"(ReqID (bounding-box 90. -180. -90. 180.)\n" +
    "  (st_constraint (block_id \"716240\"))\n" +
	// "  (products (METAR (mime-type \"text/plain\")))\n" +
    "  (products (TAF (mime-type \"text/plain\"))))\n";
	public static final String SERVER =
		"http://zowie.metnet.navy.mil/cgi-bin/oleg/server";

	class OMFHandler extends ContentHandler {
		public Object getContent(URLConnection uc) {
			String response = "";
			try {
				BufferedReader is = new BufferedReader(
					new InputStreamReader(uc.getInputStream()));
				String line;
				while ((line = is.readLine()) != null) {
					response += line;
				}
				is.close();
			} catch (IOException ex) {
				return ex.toString() + " partial response: " + response;
			}
			return response;
		}
	}

	public static void main(String[] args) throws Exception {
		Weather wx = new Weather();
		wx.process();
	}

	protected void process() throws IOException {

		System.out.println("Setting up URLConnection");

		URL u = new URL(SERVER);

		URLConnection.setContentHandlerFactory(new ContentHandlerFactory() {
			public ContentHandler createContentHandler(String type) {
				if (type.startsWith("text/x-omf"))
					return new OMFHandler();
				return null;
			}
		});
		
		URLConnection cx = u.openConnection();

		cx.setDoInput(true);
		cx.setDoOutput(true);
		cx.setAllowUserInteraction(false);
		DataOutputStream os = null;
		try {
			os = new DataOutputStream(cx.getOutputStream());

			System.out.println("Connecting the URLConnection");
			cx.connect();
			
			System.out.println("Request is:");
			System.out.println(request);
			
			System.out.println("Sending Request");
			os.writeBytes("mbl-stmt=" + URLEncoder.encode(request, "UTF-8"));
			os.flush();
			
			System.out.println("Getting the Response");
			
			Object response = cx.getContent();
			if (response instanceof InputStream)
				response = FileIO.inputStreamToString((InputStream)response);
			System.out.println(response);
		} finally {
			if (os != null)
				os.close();
		}
	}
}

