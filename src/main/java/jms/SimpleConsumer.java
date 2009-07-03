package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SimpleConsumer {
	
	public static final String QUEUE_NAME = "queue/A";

	static class MyListener implements MessageListener {
		public void onMessage(Message message) {
			TextMessage theMessage = (TextMessage) message;
			try {
				System.out.println(theMessage.getText());
			} catch (JMSException e) {
				throw new RuntimeException("JMS Error", e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Context context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context
				.lookup("ConnectionFactory");
		Destination destination = (Destination) context.lookup(QUEUE_NAME);
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(destination);
		MessageListener processor = new MyListener();
		consumer.setMessageListener(processor);
		connection.start();
		System.out.println("Listening for messages...");
	}
}
