package ejb2.musicrecording.beans;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

/**
 * MusicBean - Implementation class for an Entity Bean representing 
 * one Music Recording in the RainForest application.
 * This version uses CMP2.0.
 * Rewritten by Ian Darwin.
 */
abstract public class MusicBean implements EntityBean {

	/** No-argument constructor (required by law :-)) */
	public MusicBean() {
	};

	// Abstract, "virtual", container managed fields

	// Primary Key
	abstract public Integer getId();
	abstract public void setId(Integer id);

	abstract public String getArtist();
	abstract public void setArtist(String val);

	abstract public String getTitle();
	abstract public void setTitle(String val);

	abstract public double getPrice();
	abstract public void setPrice(double val);

	abstract public int getCategory();
	abstract public void setCategory(int val);

	// CMR field
	abstract public PublisherLocal getPublisher();
	abstract public void setPublisher(PublisherLocal val);
	abstract public void setPublisher(PublisherRemote val);

	// create/postCreate methods.

	/** Create the Music Recording. */
	public Integer ejbCreate(String artist_name, String title, 
		int cat, double value) throws CreateException {
		setId(new Integer(1234));	// XX see auto-increment how-to doc
		setArtist(artist_name);
		setTitle(title);
		setCategory(cat);
		setPrice(value);

		return null;  // Per EJB 1.1 specification
	}

	public void ejbPostCreate(String artist_name, String title,
		int cat, double value) throws CreateException { }

	// "Business" - actually conversion - methods

	public MusicRecordingDO getRecording() {
		return new MusicRecordingDO(getId().intValue(),
			getArtist(),
			getTitle(),
			getCategory(),
			MusicRecordingDO.NO_PKEY,	// XXX
			getPrice());
	}

	public void setRecording(MusicRecordingDO rec) {
		int i = rec.getId();
		if (i != MusicRecordingDO.NO_PKEY && i != getId().intValue()) {
			throw new IllegalArgumentException(
			"Try to set MusicRecordingDO(" + i + ") on recording " + getId());
		}
		setArtist   (rec.getArtist());
		setTitle    (rec.getTitle());
		setPrice    (rec.getPrice());
		// setPublisher(rec.getPublisher());
	}

	public String toString() {
		return String.format("MusicRecording[%d,%s,%s,$%f5.2]",
			getId(), getTitle(), getArtist(), getPrice());
	}

	// Callback methods.

	/** The obvious Entity Context object */
	private EntityContext ctx;

	/** Sets the EntityContext for the EJB.  */
	public void setEntityContext(EntityContext ctx) {
	  this.ctx = ctx;
	}

	/** Unset the EntityContext for the EJB.  */
	public void unsetEntityContext() {
	  this.ctx = null;
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void ejbLoad() {
	}

	public void ejbStore() {
	}

	public void ejbRemove() throws RemoveException
	{
	}

}
