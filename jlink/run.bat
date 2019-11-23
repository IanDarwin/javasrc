javac -d . *.java
jar cvf app.jar module-info.class  demo
jdeps --module-path . app.jar
jlink --module-path . --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules java.base,demo --output mini-java
mini-java/bin/java demo.Hello
