package ocx;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.beans.*;

// --------------------------------------------------------------------------------------
// pdf42
// Dispatch interface for Acrobat Control
// --------------------------------------------------------------------------------------

public class pdf42 extends Component
{
    public void Print(  )
    {
    }

    public boolean LoadFile( String fileName )
    {
        boolean retVar = false;
        return retVar;
    }

    public void AboutBox(  )
    {
    }

    // Getters
    public String getsrc() { return m_src; }

    // Setters
    public void setsrc( String src ) 
    {
        m_src = src;
    }

    // Member variables
    private String m_src;
}
