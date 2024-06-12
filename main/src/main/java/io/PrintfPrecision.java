package io;
public class PrintfPrecision {
public static void main(String[] args) {
double latitude = -79.2818182791791d;
String msg = "Latitude: %10.6f".formatted(latitude);
System.out.println(msg);
}
}
