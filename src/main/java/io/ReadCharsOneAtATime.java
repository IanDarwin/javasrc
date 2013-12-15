package io;

/** This works, but is likely to be very slow. */
// BEGIN main
public class ReadCharsOneAtATime {

    void doFile(Reader is) {
        int c;
        while ((c=is.read( )) != -1) {
            System.out.print((char)c);
        }
    }
}
// BEGIN main
