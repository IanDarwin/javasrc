import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;	// IF THIS FAILS TO COMPILE YOU NEED JDK1.4.
import javax.imageio.stream.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Servlet to draw a Graphical Chart in response to a user request
 */
public class ChartServlet extends HttpServlet {
	private static int W = 300, H = 200;

	/** Draw a Graphical Chart in response to a user request */
	public void doGet(HttpServletRequest request,
		HttpServletResponse response)
	throws IOException {

		response.setContentType("image/jpeg");

		// Create an Image
		BufferedImage img =
			new BufferedImage(W, H,
			BufferedImage.TYPE_INT_RGB);

		// Get the Image's Graphics, and draw.
		Graphics2D g = img.createGraphics();

		// In real life this would call some charting software...
		g.setColor(Color.white);
		g.fillRect(0,0, W, H);
		g.setColor(Color.green);
		g.fillOval(100, 75, 50, 50);

		// Write the output
		OutputStream os = response.getOutputStream();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);

		if (!ImageIO.write(img, "jpeg", ios)) {
			log("Boo hoo, failed to write JPEG");
		}
		ios.close();
		os.close();
	}
}
