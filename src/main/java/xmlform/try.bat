call mk.bat
if errorlevel 1 goto dontrun
call xmlform.bat test1.xml
if errorlevel 1 goto dontrun
rem vi test1.mif
start test1.xml.mif
:dontrun
