package ejb2.musicrecording.client;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb2.musicrecording.beans.MusicRemote;
import ejb2.musicrecording.beans.MusicRemoteHome;
import ejb2.musicrecording.beans.PublisherRemote;
import ejb2.musicrecording.beans.PublisherRemoteHome;

/** 
 * A simple(?) remote client showing client access to
 * Entity Beans (normally Entities are manipulated in
 * Session Beans using local access!).
 * <p>
 * The error handling here shows a worst-case scenario,
 * catching many of the EJB spread-spectrum of checked
 * exceptions.
 * @author Ian Darwin
 */
public class Client {
	public static void main(String[] args) {
		Context ctx;
		MusicRemote existingEntity, newEntity;
		MusicRemoteHome mh;
		try {
			System.out.println("Getting context");
			ctx = new InitialContext();
			System.out.println("Looking up Home in " + ctx);
			mh = (MusicRemoteHome)ctx.lookup("MusicRecording");
			System.out.println("Found Home: " + mh);

			existingEntity = mh.findByPrimaryKey(new Integer(1234));
			System.out.println("Found " + existingEntity);
			newEntity = mh.create("DarwinIan", "Greatest His/Hers", 0, 19.99);
			System.out.println("Created " + newEntity);

			PublisherRemoteHome ph = (PublisherRemoteHome)ctx.lookup("Publisher");
			PublisherRemote pub = ph.findByPrimaryKey(new Integer(3));
			System.out.println("Found publisher: " + pub);

			// Can't do this in a remote client; EJB2 CMR only 
			// works with Local interfaces!
			// mr.setPublisher(pub);
			
			if (false) {
				existingEntity.remove();
				System.out.println("Gone but not forgotten");
			}
		} catch (EJBException e) { // unchecked
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoveException e) {
			e.printStackTrace();
		}
	}
}
