package domain;

import javax.persistence.Entity;

@Entity
public class Track {
	String title;
	Duration d;
	public Track(String title, Duration duration) {
		this.title = title;
		this.d = duration;
	}
}
