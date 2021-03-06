@echo off
::
:: Script to set environment vars
::
:: $Id: env.cmd,v 1.9 2002/03/27 11:17:35 lenzmann Exp $
::

:: Get current dir


for %%i in (.) do set MY_CD=%%~Sfi

if NOT $%ENV_CALLED%$ == $$ goto done

:: Set environment

set HT_SERVER_DIR=%MY_CD%\..
set JAVA_CODE_LIB_PATH=%HT_SERVER_DIR%\lib

:: binary root - our bin directory
set BIN_ROOT=%HT_SERVER_DIR%/bin

:: adjust system path
set PATH=%PATH%;%BIN_ROOT%


:: Create Java class path
set CLASSPATH=%CLASSPATH%;%HT_SERVER_DIR%\classes
for %%i in (%JAVA_CODE_LIB_PATH%\*.jar) Do Call :setlist %%i 

set ENV_CALLED=1
GoTo :EOF



:: helper function to get file name from each iteration.
:setlist
set CLASSPATH=%CLASSPATH%;%1
GoTo :EOF 
