import com.sun.java.swing.*;
/**
 * PnmModel.java -- data model for PnmConv
 */
public class PnmView {
	public void buildGUI() {
		JFrame f = new JFrame("PnmConvert");
		f.setSize(100,100);	// instead of pack
		f.setVisible(true);
		JFileChooser fc = new JFileChooser(".");
		fc.setChoosableFileTypes(PnmTypes.fileTypes);
		fc.setOkayTitle("Convert");
		fc.showDialog(f);
	}
}
