javac %1.java

if errorlevel 1 goto norun

java  %1   %2 %3 %4 %5 %6

:norun
