package jms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/** This class implements a simple JMS Chat client.
 * Assume there is a JMS Router running that we can contact.
 * Assume that "jndi.properties" lets us look up in the JMS service.
 */
public class JMSChat extends JFrame {

	private static final long serialVersionUID = 1213151366536598385L;
	ConnectionFactory	connectionFactory;
	Connection			connection;
	Session				session;
	MessageProducer		producer;
	MessageConsumer		consumer;
	Destination			topic;

	JTextField theMessage = new JTextField(20);
	JLabel theLabel = new JLabel("Messages Sent:");
	JList theMessages;
	List<String> messageStore = new Vector<String>();

    /**
	 * Instantiate and start the thing.
	 */
	public static void main(String args[]) {
		JMSChat myChat = new JMSChat(args);
		myChat.setVisible(true);
	}

    /*
	 * Construct the Chat program
     */
	public JMSChat(String[] args) {

		setTitle("JMS Dispatcher");

		// layout the screen
		JButton dispatch = new JButton("Dispatch");
		theMessage = new JTextField();

		JPanel bottomPanel = new JPanel();
		bottomPanel.add(theMessage);
		bottomPanel.add(dispatch);

		Container cp = getContentPane();

		theMessages = new JList((Vector)messageStore);
		theMessages.setPrototypeCellValue("SomeTopicName/SomeMessageString");
		cp.add(new JScrollPane(theMessages), BorderLayout.CENTER);
		cp.add(bottomPanel, BorderLayout.SOUTH);

		cp.add(bottomPanel, BorderLayout.SOUTH);
		pack();

		// set up the queue system
		createConnection(args);

		// listener setup
		dispatch.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e){

				// dispatch the message under to the topic
				publishMessage();
			}
		});

		this.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent e) {

				// shut down nicely, then exit.
				chatClose();
				System.exit(0);
			}
		});

	}

    /** Create the TopicConnection and TopicSession
    */
	public void createConnection(String[] args) {
		try {

			Context jndiContext = new InitialContext();

			try {
				connectionFactory = (TopicConnectionFactory)
					jndiContext.lookup("ConnectionFactory");
				connection =
					connectionFactory.createConnection();
				topic = (Topic) jndiContext.lookup("topic/testTopic");
			} catch (JMSException ex) {
				JOptionPane.showMessageDialog(this,
					"Error: " + ex.toString(),
					"Error!",
					JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} catch (NamingException ex) {
				JOptionPane.showMessageDialog(this,
					"Error: " + ex.toString(),
					"Error!",
					JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

         	System.out.println("Starting topic connection");
         	connection.start();

			System.out.println("Creating topic session...");
			session = connection.createSession(false,1);
			System.out.println("Created topic session: not transacted, auto ack");

			System.out.println("Creating topic and publisher...");
			//topic = topicSession.createTopic(currentTopic);
			producer = session.createProducer(topic);
			System.out.println("Created topic and publisher");

			// Create a topic and a subscriber
			consumer = session.createConsumer(topic);
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage msg = (TextMessage) message;
					try {
						messageStore.add("Incoming: " + msg.getText());
						theMessages.setListData((Vector)messageStore);
					} catch (JMSException ex) {
						JOptionPane.showMessageDialog(JMSChat.this,
							"Error in onMessage: " + ex.toString(),
							"Error!",
							JOptionPane.ERROR_MESSAGE);
						// ex.printStackTrace();
					}
				}
			});
			connection.start();
			System.out.println("Created subscriber and started connection...");
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
				"Error creating topic objects: " + ex.toString(),
				"Error!",
				JOptionPane.ERROR_MESSAGE);
			// ex.printStackTrace();
		}
	}

    /**
	 * Send a TextMessage on the given Topic
     */
	public void publishMessage() {
		try {
			/*
			 * Create and Publish a TextMessage.
			 */
			Message textmsg1 = session.createTextMessage(
				theMessage.getText());

			// Send the message; we will get it back.
			producer.send(textmsg1);
		}
		catch (JMSException ex) {
			JOptionPane.showMessageDialog(this,
				"Error in publish: " + ex.toString(),
				"Error!",
				JOptionPane.ERROR_MESSAGE);
			// ex.printStackTrace();
		}
	}

    /**
	 * Close the JMS objects
     */
	public void chatClose() {
		try {
			// Close the publisher
			System.out.println("Closing publisher ...");
			producer.close();

			// Close the subscriber
			System.out.println("Closing subscriber ...");
			consumer.close();

			System.out.println("Closing topic session and topic connection");
			session.close();
			connection.close();
		}
		catch (JMSException ex) {
			JOptionPane.showMessageDialog(this,
				ex.toString(),
				"Error!",
				JOptionPane.ERROR_MESSAGE);
			// ex.printStackTrace();
		}
	}
}
