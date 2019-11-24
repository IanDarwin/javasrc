package netweb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * HttpDemo - Allow you to experiment with the HTTP protocol; displays inputs
 * and outputs in separate panels, with a JSplitPane to adjust the relative
 * sizes of each.
 * 
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class HttpDemo extends JFrame {

	private static final long serialVersionUID = 7603134503015353375L;

	public static void main(String[] av) {
		HttpDemo tb = new HttpDemo();
		tb.setVisible(true);
	}

	final JTextArea inputArea = new JTextArea(10, 55);

	final JTextArea outputArea = new JTextArea(40, 55);

	HttpDemo() {
		super("HttpDemo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top = new JPanel();
		top.setBackground(Color.pink);
		final JTextField host = new JTextField(20);
		host.setText("localhost");
		top.add(new JLabel("Host"));
		top.add(host);
		final JTextField port = new JTextField("8080");
		top.add(new JLabel("Port"));
		top.add(port);
		final JTextField request = new JTextField(25);
		request.setText("GET /index.jsp HTTP/1.0");
		top.add(new JLabel("HTTP Request"));
		top.add(request);
		JButton go = new JButton("Go!");
		top.add(go);

		// Split Pane divides between input and output
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		// set up bottom pane
		inputArea.setBorder(BorderFactory.createTitledBorder("Input"));
		inputArea.setEditable(false);
		split.add(inputArea, 0);
		outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		split.add(new JScrollPane(outputArea), 1);

		// set up main window
		add(top, BorderLayout.NORTH);
		add(split, BorderLayout.CENTER);
		pack();

		// attach simple controller to button
		go.addActionListener(new ActionHandler(outputArea, host, port, request));
	}

	/**
	 * ActionListener that sends the HTTP Request and displays it and the HTTP Result.
	 */
	public class ActionHandler implements ActionListener {
		PrintWriter out = null;

		final JTextField hostName;

		final JTextField portString;

		final JTextField requestString;
		
		final JTextArea outputArea;		
		

		public ActionHandler(JTextArea outputArea, JTextField host, JTextField port, JTextField request) {
			this.outputArea = outputArea;
			hostName = host;
			portString = port;
			requestString = request;
		}

		void clear() {
			inputArea.setText("");
			outputArea.setText("");
		}

		void send(String mesg) {
			out.print(mesg);
			out.print("\r\n");
			inputArea.append(mesg);
			inputArea.append("\n");
		}

		public void actionPerformed(ActionEvent e) {

			clear();

			System.out.println(hostName + "-->" + requestString);
			new Thread() {
				public void run() {

					try {
						doNetworkIO(hostName.getText(), portString.getText(), requestString.getText());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(HttpDemo.this,
								"Error during conversation: " + e, "Error",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}.start();
		}

		private void doNetworkIO(String hostName,
				String portString, String requestString)
				throws UnknownHostException, IOException {
			Socket sock = new Socket(hostName, Integer.parseInt(portString));
			out = new PrintWriter(sock.getOutputStream());
			send(requestString);
			send("Host: " + hostName);
			send("");
			out.flush();

			BufferedReader is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String line;

			while ((line = is.readLine()) != null) {
				System.out.println(line);
				outputArea.append(line);
				outputArea.append("\n");
			}
			is.close();
			sock.close();
		}
	}
}
