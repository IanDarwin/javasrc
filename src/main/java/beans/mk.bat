jikes -d . CodeBean.java
jar cvfm DarwinSysCodeBean.jar manifest.stub com/darwinsys 
java -classpath /progra~1/javasoft/jre/1.2/lib/jaws.jar sun.beans.ole.Packager -jar DarwinSysCodeBean.jar -ax "Java Code Fragment"
