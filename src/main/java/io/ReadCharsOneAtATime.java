package io;

import java.io.IOException;
import java.io.Reader;

/** This works, but is likely to be very slow. */
// BEGIN main
public class ReadCharsOneAtATime {

    void doFile(Reader is) throws IOException {
        int c;
        while ((c=is.read( )) != -1) {
            System.out.print((char)c);
        }
    }
}
// END main
