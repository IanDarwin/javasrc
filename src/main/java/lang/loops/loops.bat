jikes LoopFor.java
javap -c Loops > loopfor.txt
jikes LoopWhile.java
javap -c Loops > loopwhile.txt
diff loop*.txt