package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SimpleProducer {
	
	public final static String QUEUE_NAME = "queue/A";

	public static void main(String[] args) throws Exception {
		Context ctx = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ctx
				.lookup("ConnectionFactory");
		Destination destination = (Destination) ctx.lookup(QUEUE_NAME);
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(destination);
		
		TextMessage message = session.createTextMessage();
		message.setText("Hello World of JMS");
		producer.send(message);
		
		TextMessage message2 = 
			session.createTextMessage("Here is another message");
		producer.send(message2);
		
		producer.close();
	}
}
