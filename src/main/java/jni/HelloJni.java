package jni;

// BEGIN main
/**
 * A trivial class to show Java Native Interface 1.1 usage from Java.
  */
public class HelloJni {
  int myNumber = 42; // used to show argument passing

  // declare native class
  public native void displayHelloJni();

  // Application main, call its display method
  public static void main(String[] args) {
    System.out.println("HelloJni starting; args.length="+
                       args.length+"...");
    for (int i=0; i<args.length; i++)
                       System.out.println("args["+i+"]="+args[i]);
    HelloJni hw = new HelloJni();
    hw.displayHelloJni();// call the native function
    System.out.println("Back in Java, \"myNumber\" now " + hw.myNumber);
  }

  // Static code blocks are executed once, when class file is loaded
  static {
    System.loadLibrary("hello");
  }
}
// END main
