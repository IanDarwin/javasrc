REM FAILED ATTEMPT TO USE GCC
REM DEPENDS ON SOME FILES IN .\WIN32
set JAVAHOME=\jdk1.1
gcc -I. -I%JAVAHOME%\include -I%JAVAHOME%\include\win32 HelloWorld.c -o hello.dll -MD -LD %JAVAHOME%\lib\javai.lib
