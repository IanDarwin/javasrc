package io;

import java.nio.charset.Charset;

public class ListCharSets {
  public static void main(String[] args) {
    for (String s : Charset.availableCharsets().keySet()) {
      System.out.println(s);
    }
  }
}
