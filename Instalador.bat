ECHO OFF
ECHO Salvando caminho...
set p=%cd%

ECHO Compilando classes...
javac AluguelCarros\Models\*.java
javac AluguelCarros\Services\*.java
javac AluguelCarros\*.java

ECHO Compilando pacotes e gerando recursos...
jar cfm src.jar AluguelCarros\manifest.mf AluguelCarros lib org

del AluguelCarros\Models\*.class
del AluguelCarros\Services\*.class
del AluguelCarros\*.class 

cd c:
c:

ECHO Criando pasta do arquivo em %cd%...
mkdir "Projeto Lmf"
cd "Projeto Lmf"

ECHO Deletando arquivos antigos e instalando recursos...

del /Q src.jar > NUL
del /Q Lojas.json > NUL
del /Q "Projeto Lmf.bat" > NUL

copy "%p%\src.jar" src.jar
del "%p%\src.jar"

ECHO [{"Nome":"SouthCar", "ValorSemana":[210.00, 150.00], "ValorFds":[200.00, 90.00], "QtdPessoas":4, "Carros":["Compacto Familia 1","Compacto Familia 2","Compacto Familia 3"]},{"Nome":"WestCar", "ValorSemana":[530.00, 150.00], "ValorFds":[200.00, 90.00], "QtdPessoas":2, "Carros":["Ferrari","Camaro","Convercivel"]},{"Nome":"NorthCar", "ValorSemana":[630.00, 580.00], "ValorFds":[600.00, 590.00], "QtdPessoas":7, "Carros":["Van","Micro Onibus","Combi"]}] >> Lojas.json

echo java -jar src.jar > "Projeto Lmf.bat"
set p=%cd%
cd %userprofile%\Desktop
del "Projeto Lmf.bat"
del "reserva.txt"
copy nul reserva.txt
echo cd %p% >> "Projeto Lmf.bat"
echo cls >> "Projeto Lmf.bat"
echo java -jar src.jar >> "Projeto Lmf.bat"

ECHO Instalacao finalizada. Um atalho foi criado na area de trabalho.
TIMEOUT /T -1