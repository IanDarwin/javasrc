// This example is from the book _Java in a Nutshell_ by David Flanagan.
// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or implied.

import java.awt.*;

public class InfoDialog extends Dialog {
    protected Button button;
    protected MultiLineLabel label;

    public InfoDialog(Frame parent, String title, String message)
    {
        // Create a dialog with the specified title
        super(parent, title, true);
        
        // Create and use a BorderLayout manager with specified margins
        setLayout(new BorderLayout(15, 15));
        
        // Create the message component and add it to the window
        label = new MultiLineLabel(message, 20, 20);
        add("Center", label);
        
        // Create an Okay button in a Panel; add the Panel to the window
        // Use a FlowLayout to center the button and give it margins.
        button = new Button("OK");
        Panel p = new Panel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        p.add(button);
        add("South", p);

        // Resize the window to the preferred size of its components
        pack();
    }
    
    // Pop down the window when the button is clicked.
    public boolean action(Event e, Object arg)
    {
        if (e.target == button) {
            setVisible(false);
            dispose();
            return true;
        }
        else return false;
    }

    // When the window gets the keyboard focus, give it to the button.
    // This allows keyboard shortcuts to pop down the dialog.
    public boolean gotFocus(Event e, Object arg) {
        button.requestFocus();
        return true;
    }
    
    public static void main(String[] args) {
        Frame f = new Frame("InfoDialog Test");
        f.setSize(100, 100);
        f.setVisible(true);
        InfoDialog d = new InfoDialog(f, "Help", 
                          "The host you are trying to contact\n" +
                          "is not currently responding.\n" +
                          "Please try again later.");
        d.setVisible(true);
    }
}
