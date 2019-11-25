package ejb2.musicrecording.beans;

import javax.ejb.EJBLocalObject;

/**
 * The Local Business interface for the MusicRecording Entity EJB 2.1
 * @author Ian Darwin
 */
public interface MusicLocal extends EJBLocalObject {

	abstract public String getArtist();
	abstract public void setArtist(String val);

	abstract public String getTitle();
	abstract public void setTitle(String val);

	abstract public double getPrice();
	abstract public void setPrice(double val);

	abstract public int getCategory();
	abstract public void setCategory(int val);

	public MusicRecordingDO getRecording();
	public void setRecording(MusicRecordingDO rec);

}
