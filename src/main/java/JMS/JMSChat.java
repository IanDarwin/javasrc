import javax.jms.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.naming.*;

/** This class implements a simple JMS Chat client.
 * Assume there is a JMS Router running that we can contact.
 * Assume that "jndi.properties" lets us look up in the JMS service.
 */
public class JMSChat extends JFrame {

	TopicConnectionFactory	topicConnectionFactory;
	TopicConnection			topicConnection;
	TopicSession			topicSession;
	TopicPublisher			topicPublisher;
	TopicSubscriber			topicSubscriber;
	Topic					topic;
	Context   				jndiContext = null;

	JTextField theMessage = new JTextField(20);
	JLabel theLabel = new JLabel("Messages Sent:");
	JList theMessages;
	Vector messageStore = new Vector();


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

		theMessages = new JList(messageStore);
		theMessages.setPrototypeCellValue("SomeTopicName/SomeMessageString");
		cp.add(new JScrollPane(theMessages), BorderLayout.CENTER);
		cp.add(bottomPanel, BorderLayout.SOUTH);
		Container c = getContentPane();

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

			jndiContext = new InitialContext();

			try {
				topicConnectionFactory = (TopicConnectionFactory)
					jndiContext.lookup("MyTopicConnectionFactory");
				topicConnection =
					topicConnectionFactory.createTopicConnection();
				topic = (Topic) jndiContext.lookup("MyChat");
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
         	topicConnection.start();

			System.out.println("Creating topic session...");
			topicSession = topicConnection.createTopicSession(false,1);
			System.out.println("Created topic session: not transacted, auto ack");

			System.out.println("Creating topic and publisher...");
			//topic = topicSession.createTopic(currentTopic);
			topicPublisher = topicSession.createPublisher(topic);
			System.out.println("Created topic and publisher");

			// Create a topic and a subscriber
			topicSubscriber = topicSession.createSubscriber(topic);
			topicSubscriber.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage msg = (TextMessage) message;
					try {
						messageStore.addElement("Incoming: " + msg.getText());
						theMessages.setListData(messageStore);
					} catch (JMSException ex) {
						JOptionPane.showMessageDialog(JMSChat.this,
							"Error in onMessage: " + ex.toString(),
							"Error!",
							JOptionPane.ERROR_MESSAGE);
						// ex.printStackTrace();
					}
				}
			});
			topicConnection.start();
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
			TextMessage textmsg1 = topicSession.createTextMessage(
				theMessage.getText());

			// Send the message; we will get it back.
			topicPublisher.publish(textmsg1);
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
			topicPublisher.close();

			// Close the subscriber
			System.out.println("Closing subscriber ...");
			topicSubscriber.close();

			System.out.println("Closing topic session and topic connection");
			topicSession.close();
			topicConnection.close();
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
