@echo off
chcp 65001
echo Compiling project...

if not exist bin mkdir bin

for %%f in (code\*.java) do javac -encoding UTF-8 -d bin %%f

echo Running the program...
java -cp bin code.Main

pause
