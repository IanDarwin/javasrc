set CODEDIR=/javasrc
java -cp %CLASSPATH%;%CODEDIR% -Dcodedir=%CODEDIR% XmlForm %1 > %1.mif
