import java.text.*;
/* Print a table of fahrenheit and celsius temperatures 
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class TempConverterPrintf {
	public static void main(String unusedArgs[]) {
		DecimalFormat df = new DecimalFormat("##.###");
		for (int i=-40; i<=120; i+=10) {
			float c = (i-32)*(5f/9);
			System.out.println(i + " f = " + df.format(c) + " c.");
		}
	}
}
