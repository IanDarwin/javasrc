all:	csvgetline CSVFile.class
	csvgetline   < CSV.dat > j1
	java CSVFile < CSV.dat > j2
	-diff j1 j2

CSVFile.class: CSVFile.java CSV.java
	jikes +E CSVFile.java

csvgetline: csvgetline.c++
	$(CC) csvgetline.c++ -lstdc++ -o $@

clean:
	rm -f j1 j2 csvgetline

