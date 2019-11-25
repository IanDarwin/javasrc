package domain;

import javax.persistence.Entity;

@Entity
public class Track {
	String title;
	Duration duration;
	public Track(String title, Duration duration) {
		this.title = title;
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
