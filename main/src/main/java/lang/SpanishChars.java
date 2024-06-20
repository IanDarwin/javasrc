void main() {
for (int ch : java.util.List.of('á', 'é', 'í', 'ó', 'ú')) {
	System.out.printf("%c %x %s\n", ch, ch, Integer.toString(ch,2));
}
}
