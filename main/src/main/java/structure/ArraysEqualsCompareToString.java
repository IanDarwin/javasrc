package structure;

import java.util.Arrays;

// tag::main[]

public class ArraysEqualsCompareToString {

    void main() {
// end::main[]
        System.out.println("Permute char value");
        char[] ca = { 'a', 'b', 'c'},
                cb = { 'a', 'b', 'c'};
        System.out.println(STR."ca = \{Arrays.toString(ca)}, cb = \{Arrays.toString(cb)}");
        System.out.println(STR."By Arrays.equals(): \{Arrays.equals(ca, cb)}");
        System.out.println(STR."compare(ca,cb) returns \{Arrays.compare(ca, cb)}");
        cb[2] = 'd';
        System.out.println(STR."ca = \{Arrays.toString(ca)}, cb = \{Arrays.toString(cb)}");
        System.out.println(STR."By Arrays.equals(): \{Arrays.equals(ca, cb)}");
        System.out.println(STR."compare(ca,cb) returns \{Arrays.compare(ca, cb)}");

        System.out.println();

        // tag::main[]
        System.out.println("Permute int value");
        int[] ia = { 1, 2, 3, 4, 5},
            ib = { 1, 2, 3, 4, 5};
        System.out.println(STR."""
			ia = \{Arrays.toString(ia)}, 
			ib = \{Arrays.toString(ib)}""");
        System.out.println(STR."By Arrays.equals(): \{Arrays.equals(ia, ib)}");
        System.out.println(STR."compare(ia,ib) returns \{Arrays.compare(ia, ib)}");
        ia[4] = 6;
        System.out.println(STR."""
			ia = \{Arrays.toString(ia)},
			ib = \{Arrays.toString(ib)}""");
        System.out.println(STR."By Arrays.equals(): \{Arrays.equals(ia, ib)}");
        System.out.println(STR."compare(ia,ib) returns \{Arrays.compare(ia, ib)}");
        // end::main[]

        System.out.println();

        System.out.println("Permute order");
        ia = new int[]{ 1, 2, 3, 4, 5};
        ib = new int[]{ 1, 2, 3, 4, 5};
        System.out.println(STR."ia = \{Arrays.toString(ia)}, ib = \{Arrays.toString(ib)}");
        System.out.println(STR."By Arrays.equals(): \{Arrays.equals(ia, ib)}");
        System.out.println(STR."compare(ia,ib) returns \{Arrays.compare(ia, ib)}");
        ia[4] = 4;
        ia[3] = 5;
        System.out.println(STR."ia = \{Arrays.toString(ia)}, ib = \{Arrays.toString(ib)}");
        System.out.println(STR."By Arrays.equals(): \{Arrays.equals(ia, ib)}");
        System.out.println(STR."compare(ia,ib) returns \{Arrays.compare(ia, ib)}");
    }
}
