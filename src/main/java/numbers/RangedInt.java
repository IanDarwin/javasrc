package numbers;

/** A RangedInt is similar to Integer but enforces upper and lower bounds
 * that are set at construction time.
 * Originally for Learning Tree International Course 471.
 *
 */
public class RangedInt {
        /** Current value */
        protected int currentValue;

        /** Maximum value allowed */
        protected int maxValue;

        /** Minimum value allowed */
        protected int minValue;

        /**
         * Constructor: Stores the maximum and minimum
         * values and sets the current value to 0 or
         * the minimum value whichever is greater.
         */
        public RangedInt( int min, int max) {
            // store the supplied limits
            minValue = min;
            maxValue = max;

            // set the initial value of this object
            if (min > 0)
                currentValue = min;
            else
                currentValue = 0;
        }

        /**
         * Default Constructor: Sets the maximum and minimum
         * values to MAX_VALUE and MIN_VALUE.
         */
        public RangedInt() {
            // call the (int, int) constructor
            // with the max and min values
            this(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        /**
         * Set the current value of the object. If new
         * value is outside bounds then throw an
         * IllegalArgumentException
         */
        public void setValue ( int newValue) throws IllegalArgumentException {

            // check the new value
            if (newValue < minValue || newValue > maxValue)
                throw new IllegalArgumentException("From RangedInt");
			currentValue = newValue;
        }

        /**
         * Return the current value of the object
         */
        public int getValue() {
            // return the current value
            return currentValue;
        }
}
