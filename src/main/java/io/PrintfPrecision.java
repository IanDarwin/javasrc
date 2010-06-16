package io;
public class PrintfPrecision {
public static void main(String[] args) {
double latitude = -79.2818182791791d;
String msg = String.format("Latitude: %10.6f", latitude);
System.out.println(msg);
}
}
