package classloader;

import java.io.*;

/**
 * Read a file and print in compileable byte[] format.
 * Special-purpose; only meant for use in the ClassLoader demo exercise.
 */
public class ClassFileToBytes {

    public static void main(String[] av) {
        ClassFileToBytes c = new ClassFileToBytes();
        switch(av.length) {
            case 0: c.process(new DataInputStream(System.in));
				break;
            default:
				for (String a : av) {
                    try {
                        c.process(new DataInputStream(new FileInputStream(a)));
                    } catch (FileNotFoundException e) {
                        System.err.println(e);
                    }
				}
        }
    }

    /** print one file in compilable format */
    public void process(DataInputStream is) {
		System.out.println("/** data from dumping a compiled .class file */");
		System.out.println("private int[] data = {");
        try {
            int b = 0;
			int bnum = 0;

            while ((b=is.read()) != -1) {
                System.out.print(b + ", ");
				if (++bnum%10 == 0)
					System.out.print("\n");
            }
			System.out.print("};\n");
			System.out.println("/** data's expected length */");
			System.out.println("private final int dataLength = " + bnum + ";");
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
