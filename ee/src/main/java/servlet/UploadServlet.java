package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

	public void doPut(HttpServletRequest request,
			HttpServletResponse response)
	throws IOException, javax.servlet.ServletException {

		PrintWriter out = response.getWriter();
		Enumeration headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String name = headers.nextElement().toString();
			out.println("<li>" + name + " --> " +
				request.getHeader(name));
		}
		out.println("<HR>");
		int size = request.getIntHeader("content-length");
		// if (size > some_threshold) {
		//	out.println("<P>Too big!</P>");
		//	return;
		// }
		File f = new File("/tmp/abc.upload");
		//if (!f.createNewFile()) {
		//	out.println("File already exists");
		//	return;
		//}
		InputStream is = request.getInputStream();

		// DANGER WILL ROBINSON!! YOU MUST ADD CODE HERE TO
		// CHECK FOR TRAPS LIKE . or / or \ or : or File.separator in the filename

		OutputStream os = new FileOutputStream(f.getPath());

		byte[] b = new byte[1024];
		int n;
		while ((n = is.read(b)) > 0)
			os.write(b, 0, n);
		is.close();
		os.close();
		out.println("Successfully uploaded!");

		// Now you have to use JAF or something to
		// handle the MIME headers in the stream...
	}
}
