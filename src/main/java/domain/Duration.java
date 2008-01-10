package domain;

import javax.persistence.Entity;

@Entity
public class Duration {
	int hours, minutes, seconds;
	
	public Duration(int hours, int minutes, int seconds) {
		super();
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public Duration(int seconds) {
		this.seconds = seconds; // XXX do the math!
	}
}
