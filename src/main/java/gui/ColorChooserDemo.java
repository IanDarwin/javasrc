import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* 
 * ColorChooser - JColorChooser demo.
 * JColorChooser can be used in three ways:
 * <UL><LI>Construct it and place it in a panel;
 * <LI>Call its ConstructDialog() and get a JDialog back
 * <LI>Call its showDialog() and get back the chosen color
 * </UL>
 * @version $Id$
 */
public class ColorChooser extends JFrame
{
    JColorChooser cc;
    JDialog cd;
    MyCanvas demo;

    public ColorChooser() {
        super("Ian Darwin's Color Demo");
        JButton jButton;
		Container cPane = getContentPane();
        cPane.add("North", jButton = new JButton("Choose Color..."));
		jButton.setToolTipText("Click here to see the Color Chooser");
        jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent)
			{
				ColorChooser.this.cd.setVisible(true);
			}
		});
        cPane.add("Center", demo = new MyCanvas(300,150));
		demo.setToolTipText("This is the last color you chose");
        pack();
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});
        cd = JColorChooser.createDialog(this,
			"Ian Darwin's Color PopUp",
			true,
			cc = new JColorChooser(getBackground()),
			new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					ColorChooser.this.demo.setColor(cc.getColor());
				}
			},
			new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent)
				{
					ColorChooser.this.demo.setColor(getBackground());
				}
			});
	}

    public static void main(String astring[])
    {
        new ColorChooser().setVisible(true);
    }
}

class MyCanvas extends JComponent {
	int width, height;
	Color col = Color.white;
	protected Dimension preferredSize;

	MyCanvas(int w, int h) {
		preferredSize = new Dimension(width = w, height = h);
		setSize(preferredSize);
	}
	public Dimension getPreferredSize() {
		return preferredSize;
	}
	void setColor(Color c) {
		col = c;
		repaint();
	}
	public void paint(Graphics g) {
		Dimension d = getSize();
		g.setColor(col);
		g.fillRect(0, 0, d.width-1, d.height-1);
	}
}
