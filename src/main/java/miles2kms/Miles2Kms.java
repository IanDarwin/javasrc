package miles2kms;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

/**
 * A simple applet panel - miles <==> kilometers
 * @author Ian Darwin, based loosely on a long-ago temperature demo 
 * by Arthur van Hoff.
 * Reworked as an "observable" demo after an example (FtoC) from Course 477.
 * Should add text listeners on the textfields...
 */
public class Miles2Kms extends Applet {
	Scrollbar sb;
	LabelTextField miles, kms;
	MyModel model;
	
	public void init() {
		Panel pl, pr;
		pl = new Panel();
		pr = new Panel();
		pl.setLayout(new BorderLayout());
		pl.add(BorderLayout.NORTH, miles = new LabelTextField('m', "Miles", 10));
		pr.add(sb = new Scrollbar(Scrollbar.VERTICAL, 0, 1, -240, 0)); 
		pl.add(BorderLayout.SOUTH, kms = new LabelTextField('k', "Kilometers", 10));
		add(pr);
		add(pl);

		model = new MyModel(); 
		model.addObserver(miles);
		model.addObserver(kms);

		// tell sb to drive the adjustListener
		sb.addAdjustmentListener( new MyAdjustmentListener( model ) );
	}

	public static void main(String[] av) {
		Miles2Kms mk = new Miles2Kms();
		Frame f = new Frame("Miles2Kms");
		f.add(mk);
		mk.init();
		f.pack();
		f.setVisible(true);
	}
}

/** A label and a text field together */
class LabelTextField extends Panel implements Observer {
	Label l;
	TextField tf;
	int type;
	LabelTextField(int type, String label, int tfWidth) {
		this.type = type;
		add(l = new Label(label));
		add(tf = new TextField(tfWidth));
		tf.setEditable(false);
		setText("0");
	}
	public void setText(String s) {
		tf.setText(s);
	}
	public String getText() {
		return tf.getText();
	}
	public void update( Observable obs, Object x ) {
		switch(type) {
		case 'm':
			setText(String.valueOf(((MyModel)obs).getMiles()));
			break;
		case 'k':
			setText(String.valueOf(((MyModel)obs).getKms()));
			break;
		}
	}
}

class MyModel extends Observable {
	private int miles;
	public void setMiles( int m ) {
		miles = m;
		// Notify observers of change
		setChanged();
		notifyObservers();
	}
	public int getMiles() {
		return miles;
	}
	public double getKms() {
		return miles*1.6;
	}
	public void setKms(int kms) {
		miles = (int)(kms * 0.61);
		setChanged();
		notifyObservers();
	}
}

class MyAdjustmentListener implements AdjustmentListener {
	MyModel model;
	MyAdjustmentListener( MyModel model ) {
		this.model = model;
	}
	public void adjustmentValueChanged( AdjustmentEvent e ) {
		model.setMiles( -e.getValue() );
	}
}
