import com.sun.java.swing.*;
/**
 * PnmConv.java -- main program for PNM Converter
 */
public class PnmConv {
	public static void main(String[] argv) {
		if (argv.length == 0)
			new PnmView().buildGUI();
		else {
			String toType = argv[argv.length-1];
			for (int i=0; i<argv.length-1; i++)
				PnmModel.convert(argv[i], toType);
		}
	}
}
