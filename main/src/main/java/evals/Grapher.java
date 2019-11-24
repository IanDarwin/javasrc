package evals;

import java.awt.*;
import javax.swing.*;

public class Grapher extends JPanel {
	JProgressBar counter, crsScore, insScore;
	public Grapher() {
		setLayout(new GridLayout(3,2));
		add(new JLabel("Course: ", JLabel.RIGHT));
		crsScore = new JProgressBar();
		crsScore.setValue(0);
		crsScore.setMinimum(0);
		crsScore.setMaximum(40);
		crsScore.getAccessibleContext().
			setAccessibleName("Course Grade");
		add(crsScore);

		add(new JLabel("Instructor: ", JLabel.RIGHT));
		insScore = new JProgressBar();
		insScore.setValue(0);
		insScore.setMinimum(0);
		insScore.setMaximum(40);
		insScore.getAccessibleContext().
			setAccessibleName("Instructor Grade");
		add(insScore);

		add(new JLabel("Count: ", JLabel.RIGHT));
		counter = new JProgressBar();
		counter.setValue(0);
		counter.setMinimum(0);
		counter.setMaximum(20);
		counter.getAccessibleContext().
			setAccessibleName("Number of evaluations turned in so far");
		add(counter);
	}

	public void setCount(int nstudents) {
		counter.setMaximum(nstudents);
	}

	public void setValues(int nEvals, float crs, float ins) {
		counter.setValue(nEvals);
		crsScore.setValue((int)(10*crs));
		insScore.setValue((int)(10*ins));
	}
}
