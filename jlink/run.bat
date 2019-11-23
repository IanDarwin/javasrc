REM Compile the application code
javac -d . *.java
REM Jar it up
jar cvf app.jar module-info.class  demo
REM Run jdeps to get a list of needed modules
jdeps --module-path . app.jar
REM Run jlink to build a mini-java distribution with our app imbedded
jlink --module-path . --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules java.base,demo --output mini-java
REM Try running it
mini-java\bin\java demo.Hello
REM Create a startup script
echo 'mini-java\bin\java demo.Hello' > mini-java\demo
chmod a+x mini-java\demo
REM Run that
mini-java\demo
