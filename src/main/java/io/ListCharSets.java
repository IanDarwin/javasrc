import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class ListCharSets {
  public static void main(String[] args) {
    Set set = Charset.availableCharsets().keySet();
    for (Iterator iterator = set.iterator(); iterator.hasNext();) {
      String s = (String) iterator.next();
      System.out.println(s);
    }
  }
}
