package numbers;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

/** Compute ax^2+2b on double arrays */
public class VectorOps {
    public static void main(String[] args) {
        System.out.println(STR."Using Vector API with SPECIES \{SPECIES}");
        double[] a = { 2, 4, 6, 8, 9, 10, 11, 12, 13, 14};
        double x = Math.PI;
        double[] b = { 1, 3, 5, 7, 9, 10, 11, 12, 13, 14};
        double[] sresults = new double[a.length], vresults = new double[a.length];
        System.out.println(STR."Inputs: a=\{arrayToString(a)}, x = \{x}, b = \{arrayToString(b)}");
        scalarComputation(a, x, b, sresults);
        System.out.println(STR."Results from scalar = \{arrayToString(sresults)}");
        vectorComputation(a, x, b, vresults);
        System.out.println(STR."Results from vector = \{arrayToString(vresults)}");
        for (int i = 0; i < sresults.length; i++) {
            if (Math.abs(sresults[i] - vresults[i]) > 0.000001D) {
                throw new IllegalStateException(
                        STR."Values differ: \{sresults[i]} != \{vresults[i]}");
            }
        }
        System.out.println("Computed both ways: Close enough!");
    }

    static String arrayToString(double[] array) {
        var sb = new StringBuilder("[");
        for (double f : array) {
            sb.append(f).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    static void scalarComputation(double[] a, double x, double[] b, double[] c) {
        for (int i = 0; i < a.length; i++) {
            c[i] = (a[i] * x * x + b[i] * 2);
        }
    }

    static final VectorSpecies<Double> SPECIES = DoubleVector.SPECIES_PREFERRED;

    static void vectorComputation(double[] a, double x, double[] b, double[] c) {
        int i = 0;
        int upperBound = SPECIES.loopBound(a.length);
        for (; i < upperBound; i += SPECIES.length()) {
            var va = DoubleVector.fromArray(SPECIES, a, i);
            var vb = DoubleVector.fromArray(SPECIES, b, i);
            var vc = va.mul(x*x)
                    .add(vb.mul(2));
            vc.intoArray(c, i);
        }
        for (; i < a.length; i++) {
            c[i] = (a[i] * x * x + b[i] * 2);
        }
    }
}
